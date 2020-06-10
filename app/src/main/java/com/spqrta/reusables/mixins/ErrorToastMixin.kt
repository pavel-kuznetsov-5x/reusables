package com.spqrta.reusables.mixins

import com.spqrta.reusables.utility.CustomApplication
import com.spqrta.reusables.base.JustError
import com.spqrta.reusables.base.Payload
import com.spqrta.reusables.base.State
import com.spqrta.reusables.utility.Toaster

interface ErrorToastMixin {

    fun applyErrorToastMixin(state: State<Payload>) {
        if (state is JustError) {
            CustomApplication.analytics().logException(state.exception)
            applyErrorToastMixin(state.exception)
        }
    }

    fun applyErrorToastMixin(exception: Throwable) {
        Toaster.show(exception.message ?: "Unknown error")
    }

}