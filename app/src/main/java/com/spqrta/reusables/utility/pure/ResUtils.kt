package com.spqrta.reusables.utility.pure

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.spqrta.reusables.utility.CustomApplication

object ResUtils {

    fun getString(@StringRes res: Int): String {
        return CustomApplication.context.getString(res)
    }

    fun getColor(@ColorRes res: Int): Int {
        return CustomApplication.context.resources.getColor(res)
    }

}