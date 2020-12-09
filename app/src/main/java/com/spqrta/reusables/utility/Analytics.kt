package com.spqrta.reusables.utility

import com.spqrta.reusables.base.network.BackendException
import com.spqrta.reusables.base.network.NetworkError
import retrofit2.HttpException
import com.spqrta.reusables.utility.utils.isNetworkError

abstract class Analytics {

    open fun logException(e: Throwable, text: String? = null) {
        if (CustomApplication.appConfig.throwInAnalytics) {
            throw e
        }
        val logText = when(e) {
            is BackendException -> {
                "$e $text"
            }
            is HttpException -> {
                e.response()?.raw()?.request()?.url().toString() + "\n\t" + text
            }
            else -> text
        }

        if (!e.isNetworkError() && e !is NetworkError) {
            if (CustomApplication.appConfig.releaseMode
                || CustomApplication.appConfig.sendErrorsToAnalyticsInDebugMode
            ) {
                logExceptionToAnalytics(e, logText)
            }
        }
    }

    abstract fun logExceptionToAnalytics(e: Throwable, text: String? = null)
}