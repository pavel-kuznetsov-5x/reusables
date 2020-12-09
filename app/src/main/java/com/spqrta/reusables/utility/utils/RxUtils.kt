package com.spqrta.reusables.utility.utils

import android.view.View
import io.reactivex.Single

//todo reusables
//todo collect form others

object RxUtils {
    fun initialSingle(): Single<Stub> {
        return Single.just(Stub)
    }

    fun runInBackground(runnable: () -> Unit): Single<Stub> {
        return Single.fromCallable { runnable.invoke() }
            .map { Stub }
            .applySchedulers()
    }
}

class TaskFailedException(): Exception()

//fun <T> Task<T>.toSingle(): Single<T> {
//    val subject = SingleSubject.create<T>()
//    addOnCompleteListener {
//        if(it.isSuccessful) {
//            subject.onSuccess(it.result)
//        } else {
//            subject.onError(TaskFailedException())
//        }
//    }
//    return subject
//}

fun <T> Single<T>.disableClicksWhenLoading(vararg views: View): Single<T> {
    return doOnSubscribe {
        views.forEach {
            it.isClickable = false
        }
    }.doOnEvent { t1, t2 ->
        views.forEach {
            it.isClickable = true
        }
    }
}