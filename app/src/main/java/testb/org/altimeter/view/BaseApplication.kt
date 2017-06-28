package testb.org.altimeter.view

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class BaseApplication : Application() {
    private lateinit var refWatcher: RefWatcher

    companion object {
        fun getRefWatcher(context: Context): RefWatcher {
            val application = context.applicationContext as BaseApplication
            return application.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        refWatcher = LeakCanary.install(this)
    }
}