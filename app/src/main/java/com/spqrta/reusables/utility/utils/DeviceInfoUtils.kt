package com.spqrta.reusables.utility.utils

import android.annotation.SuppressLint
import android.os.Build

@SuppressLint("DefaultLocale")
object DeviceInfoUtils {


    fun getModel(): String {
        return Build.MODEL.toUpperCase()
    }

    fun getManufacturer(): String {
        return Build.MANUFACTURER.toUpperCase()
    }

    fun getModelAndManufacturer(): String {
        return "${getManufacturer()} ### ${getModel()}"
    }

    const val SAMSUNG_A20_A205 = "SAMSUNG ### SM-A205FN"
    const val SAMSUNG_A20_A207 = "SAMSUNG ### SM-A207F"
    const val HUAWEI_ELE_L29 = "HUAWEI ### ELE-L29"

}