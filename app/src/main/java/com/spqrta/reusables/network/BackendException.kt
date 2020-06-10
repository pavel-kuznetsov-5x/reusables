package com.spqrta.reusables.network

import com.spqrta.reusables.utility.CustomApplication
import com.spqrta.reusables.R

open class BackendException(val userText: String?, message: String?): RuntimeException(message) {

    override val message: String?
        get() = toString()

    private val serverErrorText by lazy {
        CustomApplication.context.resources.getString(R.string.server_error)
    }

    override fun toString(): String {
        val text = userText ?: (message ?: "")
        return "$serverErrorText: $text"
    }
}