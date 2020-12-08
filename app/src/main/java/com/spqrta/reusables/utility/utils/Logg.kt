package com.spqrta.camera2.utils

import android.util.Log

object Logg {

    //todo
    private val logEnabled = true

    const val TAG = "cutag"

    fun d(obj: Any?) {
        if(logEnabled) {
            Log.d(TAG, obj.toString())
        }
    }

    fun v(obj: Any?) {
        if(logEnabled) {
            Log.v(TAG, obj.toString())
        }
    }

    fun e(obj: Any) {
        if(logEnabled) {
            Log.e(TAG, obj.toString())
        }
    }

}