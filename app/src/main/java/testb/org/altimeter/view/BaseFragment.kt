package testb.org.altimeter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {
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
}