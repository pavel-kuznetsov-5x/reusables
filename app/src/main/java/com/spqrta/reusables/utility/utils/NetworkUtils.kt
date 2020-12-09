package com.spqrta.reusables.utility.utils

import com.spqrta.reusables.base.network.BackendException
import com.spqrta.reusables.utility.CustomApplication
import com.spqrta.reusables.base.network.NetworkError
import com.spqrta.reusables.utility.pure.Stub
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.CompositeException
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> Single<Response<T>>.mapResponseBody(): Single<T> {
    return flatMap {
        if (it.isSuccessful) {
            Single.just(it.body())
        } else {
            Single.error(HttpException(it))
        }
    }
}

fun <T> Single<Response<T>>.mapVoidBody(): Single<Stub> {
    return flatMap {
        if (it.isSuccessful) {
            Single.just(Stub)
        } else {
            Single.error(HttpException(it))
        }
    }
}



fun <T> Single<T>.simulateError(e: Throwable = Exception("Test exception")): Single<T> {
    return flatMap { Single.error<T>(e) }
}

fun <T> Response<T>.getBodyString(): String {
    return this.raw().body()!!.string()
}

fun <T> Single<T>.printErrors(): Single<T> {
    return doOnError {
        it.printStackTrace()
    }
}

fun <T> Single<T>.sendErrorsToAnalytics(): Single<T> {
    return doOnError {
        if (it !is BackendException) {
            CustomApplication.analytics().logException(it)
        }
    }
}

fun <T> Single<T>.applyGlobalTransformer(): Single<T> {
    return applySchedulers()
        .sendErrorsToAnalytics()
}

fun <T> Single<T>.applyNetworkErrorTransformer(): Single<T> {
    return onErrorResumeNext {
        if (it.isNetworkError()) {
            Single.error(NetworkError())
        } else {
            Single.error(it)
        }
    }
}

fun Throwable.isNetworkError(): Boolean {
    return isThrowableNetworkError(this)
}

@Suppress("RedundantIf")
private fun isThrowableNetworkError(e: Throwable): Boolean {
    return when (e) {
        is SocketTimeoutException,
        is UnknownHostException,
        is ConnectException -> {
            true
        }
        is RuntimeException -> {
            if (e is CompositeException) {
                e.exceptions.any {
                    it.isNetworkError()
                }
            } else if (e.message?.contains("Looper.prepare()") == true) {
                true
            } else {
                false
            }
        }
        else -> {
            false
        }
    }
}