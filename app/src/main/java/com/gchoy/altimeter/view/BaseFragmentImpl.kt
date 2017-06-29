package com.gchoy.altimeter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.gchoy.altimeter.service.AltimeterService
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragmentImpl : Fragment() {
    protected val compositeDeposible = CompositeDisposable()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        getPresenter().attachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().detachView()
        compositeDeposible.clear()

        val refWatcher = BaseApplication.getRefWatcher(activity)
        refWatcher.watch(this)
    }

    protected abstract fun getPresenter(): BasePresenter

    protected fun getAltimeterService(): AltimeterService {
        val mainActivity = activity as MainActivity
        return mainActivity.getAltimeterService()
    }
}