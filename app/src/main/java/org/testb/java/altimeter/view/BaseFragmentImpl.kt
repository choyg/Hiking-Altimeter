package org.testb.java.altimeter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.disposables.CompositeDisposable
import org.testb.java.altimeter.service.AltimeterService

abstract class BaseFragmentImpl : Fragment() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        getPresenter().attachView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().detachView()
        compositeDisposable.clear()

        val refWatcher = BaseApplication.getRefWatcher(activity)
        refWatcher.watch(this)
    }

    protected abstract fun getPresenter(): BasePresenter

    protected fun getAltimeterService(): AltimeterService {
        val mainActivity = activity as MainActivity
        return mainActivity.getAltimeterService()
    }

    protected fun getSharedPref(): RxSharedPreferences {
        val mainActivity = activity as MainActivity
        return mainActivity.getSharedPref()
    }
}