package org.testb.java.altimeter.view.calibrate

import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.disposables.CompositeDisposable
import org.testb.java.altimeter.*
import org.testb.java.altimeter.Unit
import org.testb.java.altimeter.service.AltimeterService

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
        var knownAltitude = altitudeString.toDouble()
        if (unitPref.get() == Unit.ft) {
            knownAltitude = convertFeetToMeters(knownAltitude)
        }
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
        view.save(altitudeString)
    }

}