package com.spqrta.reusables.mixins

import android.view.View
import com.spqrta.reusables.base.JustLoading
import com.spqrta.reusables.base.Payload
import com.spqrta.reusables.base.State
import com.spqrta.reusables.utility.utils.hide
import com.spqrta.reusables.utility.utils.show

interface ProgressbarMixin {

    fun applyProgressbarMixin(state: State<Payload>, progressbarView: View) {
        if (state is JustLoading) {
            progressbarView.show()
        } else {
            progressbarView.hide()
        }
    }

}