package com.spqrta.reusables.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spqrta.reusables.R
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable

    //todo
    open fun attachCommonDelegates(fragment: BaseFragment<BaseActivity>)
            : List<BaseFragment.FragmentDelegate<BaseActivity>> {
        return listOf()
    }

    protected open val layoutRes = R.layout.activity_main

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
        setContentView(layoutRes)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun <T> Single<T>.subscribeManaged(onSuccess: (T) -> Unit): Disposable {
        val disposable = subscribe(onSuccess)
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> Single<T>.subscribeManaged(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        val disposable = subscribe(onSuccess, onError)
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> Observable<T>.subscribeManaged(onSuccess: (T) -> Unit): Disposable {
        val disposable = subscribe(onSuccess)
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> Observable<T>.subscribeManaged(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        val disposable = subscribe(onSuccess, onError)
        compositeDisposable.add(disposable)
        return disposable
    }

}


