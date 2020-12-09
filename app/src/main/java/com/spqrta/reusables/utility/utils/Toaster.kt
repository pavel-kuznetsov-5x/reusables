package com.spqrta.reusables.utility.utils

import android.widget.Toast
import androidx.annotation.StringRes
import com.spqrta.reusables.utility.CustomApplication

class Toaster {
    companion object {
        fun show(throwable: Throwable, length: Int = Toast.LENGTH_SHORT) {
            show(throwable.message ?: "${throwable::class.java.simpleName}, no message", length)
        }

        fun show(text: String, length: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(CustomApplication.context, text, length).show()
        }

        fun show(@StringRes res: Int, length: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(
                CustomApplication.context,
                CustomApplication.context.resources.getString(res),
                length
            ).show()
        }

        fun showWithDebug(@StringRes res: Int, debugText: String, length: Int = Toast.LENGTH_SHORT) {
            if(CustomApplication.appConfig.releaseMode) {
                show(res, length)
            } else {
                show(debugText, length)
            }
        }

        fun showWithDebug(@StringRes res: Int, throwable: Throwable, length: Int = Toast.LENGTH_SHORT) {
            if(CustomApplication.appConfig.releaseMode) {
                show(res, length)
            } else {
                show(throwable, length)
            }
        }
    }
}