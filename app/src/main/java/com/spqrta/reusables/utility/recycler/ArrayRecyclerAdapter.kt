package com.spqrta.reusables.utility.recycler

import android.view.View
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_base.view.*

class ArrayRecyclerAdapter<T: Any>: BaseAdapter<T, BaseAdapter.BaseVh<T>>() {

//todo don't use layout

    override val itemLayoutResource: Int = com.spqrta.reusables.R.layout.item_base

    class VhText<R: Any>(override val containerView: View, listener: (Int) -> Unit): BaseVh<R>(containerView, listener), LayoutContainer {
        override fun bind(item: R) {
            containerView.text.text = item.toString()
        }
    }
}

