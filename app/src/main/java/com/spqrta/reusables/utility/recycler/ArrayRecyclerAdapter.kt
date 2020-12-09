package com.spqrta.reusables.utility.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.spqrta.reusables.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_base.view.*

//todo test
open class ArrayRecyclerAdapter<T: Any>: BaseAdapter<T, BaseAdapter.BaseVh<T>>() {

    override val itemLayoutResource: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVh<T> {
        return createViewHolder(
            FrameLayout(parent.context).apply {
                addView(TextView(parent.context))
            }
        ) { position ->
            onItemClickListener?.invoke(items[position])
        }
    }

    override fun createViewHolder(view: View, baseClickListener: (Int) -> Unit): BaseVh<T> {
        return VhText(view, baseClickListener)
    }

    open inner class VhText<R: Any>(override val containerView: View, listener: (Int) -> Unit): BaseVh<R>(containerView, listener), LayoutContainer {
        override fun bind(item: R) {
            ((containerView as ViewGroup).getChildAt(0) as TextView).text = item.toString()
        }
    }
}

