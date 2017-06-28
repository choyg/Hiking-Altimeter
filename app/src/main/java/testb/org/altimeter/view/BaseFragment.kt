package testb.org.altimeter.view

import android.support.v4.app.Fragment

class BaseFragment : Fragment() {
    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = BaseApplication.getRefWatcher(activity)
        refWatcher.watch(this)
    }
}