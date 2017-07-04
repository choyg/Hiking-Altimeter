package org.testb.java.altimeter.service

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import org.testb.java.altimeter.*
import org.testb.java.altimeter.Unit

class AltimeterService(val sensorManager: SensorManager, preferences: RxSharedPreferences) {
    val sensorDelay = 100
    val decimalPref = preferences.getString(PREF_DECIMALS, "0")
    val unitsPref = preferences.getEnum(PREF_UNITS, Unit.m, Unit::class.java)
    val qnhPrefTime = preferences.getLong(PREF_CALIBRATION_TIME, 0)
    val qnhPrefPressure = preferences.getFloat(PREF_CALIBRATION_PRESSURE, DEFAULT_SEA_PRESSURE.toFloat())
    val currentPressure = preferences.getFloat(PREF_CURRENT_PRESSURE, DEFAULT_SEA_PRESSURE.toFloat())

    private val altitudeObservable = Observable.create { emitter: ObservableEmitter<Altitude> ->
        val pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

            override fun onSensorChanged(p0: SensorEvent?) {
                p0?.let {
                    currentPressure.set(p0.values[0])
                    var altitude = calculateAltitude(currentPressure.get().toDouble(), qnhPrefPressure.get().toDouble())
                    val unit = unitsPref.get()
                    if (unit == Unit.ft) {
                        altitude = convertMetersToFeet(altitude)
                    }
                    val formattedAltitude = format(altitude)
                    emitter.onNext(Altitude(formattedAltitude, unitsPref.get().name))
                }
            }
        }
        sensorManager.registerListener(listener, pressureSensor, sensorDelay)

        emitter.setCancellable {
            sensorManager.unregisterListener(listener)
        }
    }

    fun format(double: Double): String {
        return String.format("%.${decimalPref.get().toInt()}f", double)
    }

    fun getAltitude(): Observable<Altitude> {
        return altitudeObservable
    }

    fun getPressureOnce(): Double {
        val pressure = currentPressure.get()
        println(pressure)
        return pressure.toDouble()
    }
}