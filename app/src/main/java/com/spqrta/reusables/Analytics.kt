package com.spqrta.reusables

import retrofit2.HttpException
import com.spqrta.reusables.network.isNetworkError

abstract class Analytics {

    open fun logException(e: Throwable, text: String? = "") {
        if (CustomApplication.appConfig.throwInAnalytics) {
            throw e
        }
        val logText = if (e is HttpException) {
            e.response()?.raw()?.request()?.url().toString() + "\n\t" + text
        } else {
            text
        }

        if (!e.isNetworkError()) {
            if (CustomApplication.appConfig.releaseMode
                || CustomApplication.appConfig.sendErrorsToAnalyticsInDebugMode
            ) {
                logExceptionToAnalytics(e, logText)
            }
        }
    }

    open fun logExceptionToAnalytics(e: Throwable, text: String? = null) {
        e.printStackTrace()
    }
}