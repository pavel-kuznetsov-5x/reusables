package com.spqrta.reusables

import com.spqrta.reusables.utility.CustomApplication

class MyApplication : CustomApplication() {
    override fun createAppConfig(): AppConfig = if (!BuildConfig.DEBUG) {
        AppConfig()
    } else {
        AppConfig(debugMode = true)
    }
}