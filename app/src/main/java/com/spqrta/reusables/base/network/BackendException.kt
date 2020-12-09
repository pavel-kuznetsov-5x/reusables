package com.spqrta.reusables.base.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import com.spqrta.reusables.utility.CustomApplication
import com.spqrta.reusables.utility.utils.AppUtils

//todo reusables
open class BackendException(
    val code: Int,
    val response: Response<*>?,
    private val errorBody: String?
) : RuntimeException() {

    private val jsonBody by lazy {
        try {
            if (errorBody != null) {
                JSONObject(errorBody)
            } else {
                JSONObject("{message:\"No message\"}")
            }
        } catch (e: JSONException) {
            JSONObject("{message:\"No message\"}")
        }
    }

    override val message: String?
        get() = jsonBody.getString("message")

    override fun toString(): String {
        //todo move parsing out
        return if(jsonBody.has("errors")) {
            try {
                jsonBody.getJSONObject("errors").keys().asSequence().map {
                    jsonBody.getJSONObject("errors").getJSONArray(it).get(0)
                }.joinToString("\n")
            } catch (e: JSONException) {
                CustomApplication.analytics().logException(e)
                message ?: "No message"
            }
        } else {
            return AppUtils.ifReleaseModeElse({
                message ?: "No message"
            }, {
                "${code} ${response?.raw()?.request()?.url()}: ${message}"
            })
        }
    }
}