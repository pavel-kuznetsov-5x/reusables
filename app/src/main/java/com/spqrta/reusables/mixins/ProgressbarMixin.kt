package com.spqrta.reusables.mixins

import android.view.View
import com.spqrta.camera2.utils.hide
import com.spqrta.camera2.utils.show
import com.spqrta.reusables.base.JustLoading
import com.spqrta.reusables.base.Payload
import com.spqrta.reusables.base.State

interface ProgressbarMixin {

    fun applyProgressbarMixin(state: State<Payload>, progressbarView: View) {
        if (state is JustLoading) {
            progressbarView.show()
        } else {
            progressbarView.hide()
        }
    }

}