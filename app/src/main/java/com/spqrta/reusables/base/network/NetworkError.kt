package com.spqrta.reusables.base.network

import com.spqrta.reusables.R
import com.spqrta.reusables.utility.CustomApplication

class NetworkError: Throwable() {

    override val message: String?
        get() = CustomApplication.context.getString(R.string.network_error)
}