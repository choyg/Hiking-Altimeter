package com.gchoy.altimeter.view.calibrate

import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.gchoy.altimeter.*
import com.gchoy.altimeter.Unit
import com.gchoy.altimeter.service.AltimeterService
import io.reactivex.disposables.CompositeDisposable

class CalibratePresenterImpl(val view: CalibrateView,
                             preference: RxSharedPreferences,
                             val altimeterService: AltimeterService
) : CalibratePresenter {

    val compositeDisposable = CompositeDisposable()
    val unitPref = preference.getEnum(PREF_UNITS, Unit.m, Unit::class.java)
    val calibrationPresurePref = preference.getFloat(PREF_CALIBRATION_PRESSURE)
    val calibrationTimepref = preference.getLong(PREF_CALIBRATION_TIME)
    var altitudeString = "0"

    override fun attachView() {
        view.setCalibrationText(altitudeString, unitPref.get())
        unitPref.asObservable()
                .subscribe {
                    view.setCalibrationText(altitudeString, it)
                }
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun setCalibration() {
        val knownAltitude = altitudeString.toDouble()
        val qnh = calculateQNH(knownAltitude, altimeterService.getPressureOnce())
        calibrationPresurePref.set(qnh.toFloat())
        calibrationTimepref.set(System.currentTimeMillis())
    }

    override fun clearCalibration() {
        altitudeString = "0"
        view.setCalibrationText(altitudeString, unitPref.get())
    }

    override fun appendCalibration(char: Char) {
        if (altitudeString.length > 8 || (char == '.' && altitudeString.contains('.')))
            return
        if (altitudeString == "0" && char != '.') {
            altitudeString = char.toString()
        } else {
            altitudeString += char

        }
        view.setCalibrationText(altitudeString, unitPref.get())
        view.save(altitudeString)
    }

    override fun onLoad(altitude: String) {
        altitudeString = altitude
        view.setCalibrationText(altitudeString, unitPref.get())
    }

}