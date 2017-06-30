package com.gchoy.altimeter.view

import android.app.Application
import android.content.Context
import android.hardware.SensorManager
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.gchoy.altimeter.service.AltimeterService
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher


class BaseApplication : Application() {
    private lateinit var refWatcher: RefWatcher

    companion object {

        fun getRefWatcher(context: Context): RefWatcher {
            val application = context.applicationContext as BaseApplication
            return application.refWatcher
        }

        fun getAltimeterService(context: Context): AltimeterService {
            val application = context.applicationContext as BaseApplication
            val manager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val rxPreferences = getSharedPref(context)
            return AltimeterService(manager, rxPreferences)
        }

        fun getSharedPref(context: Context): RxSharedPreferences {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val rxPreferences = RxSharedPreferences.create(preferences)
            return rxPreferences
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