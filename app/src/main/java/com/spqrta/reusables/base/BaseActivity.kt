package com.spqrta.reusables.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.spqrta.reusables.R

open class BaseActivity : AppCompatActivity() {

    open fun attachCommonDelegates(fragment: BaseFragment<BaseActivity>)
            : List<BaseFragment.FragmentDelegate<BaseActivity>> {
        return listOf()
    }

    protected open val layoutRes = R.layout.activity_base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    protected open fun displayFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = createReplaceTransaction(fragment)
        if (addToBackStack) {
            transaction.addToBackStack(fragment.tag)
        }
        transaction.commit()
    }

    protected fun createReplaceTransaction(fragment: Fragment): FragmentTransaction {
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
    }

}


