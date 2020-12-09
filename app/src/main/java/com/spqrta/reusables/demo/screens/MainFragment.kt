package com.spqrta.reusables.demo.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spqrta.reusables.MainActivity
import com.spqrta.reusables.R
import com.spqrta.reusables.base.display.BaseFragment
import com.spqrta.reusables.utility.pure.setLinearLayoutManager
import com.spqrta.reusables.utility.recycler.ArrayRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainActivity>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.setLinearLayoutManager(mainActivity())
        rv.adapter = ArrayRecyclerAdapter<String>().apply {
            updateItems(listOf(
                "Test 1",
                "Test 2",
            ))
        }
    }
}