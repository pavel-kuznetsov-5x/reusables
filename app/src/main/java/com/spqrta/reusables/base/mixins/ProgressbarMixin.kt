package com.spqrta.reusables.base.mixins

import android.view.View
import com.spqrta.reusables.base.display.JustLoading
import com.spqrta.reusables.base.display.Payload
import com.spqrta.reusables.base.display.State
import com.spqrta.reusables.utility.pure.hide
import com.spqrta.reusables.utility.pure.show

interface ProgressbarMixin {

    fun applyProgressbarMixin(state: State<Payload>, progressbarView: View) {
        if (state is JustLoading) {
            progressbarView.show()
        } else {
            progressbarView.hide()
        }
    }

}