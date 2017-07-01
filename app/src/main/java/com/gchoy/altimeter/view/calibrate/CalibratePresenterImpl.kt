package com.gchoy.altimeter.view.calibrate

import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.gchoy.altimeter.PREF_UNITS
import com.gchoy.altimeter.Unit
import io.reactivex.disposables.CompositeDisposable

class CalibratePresenterImpl(val view: CalibrateView,
                             val preference: RxSharedPreferences
) : CalibratePresenter {

    val compositeDisposable = CompositeDisposable()
    val unitPref = preference.getEnum(PREF_UNITS, Unit.m, Unit::class.java)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        view.setCalibrationText(altitudeString, Unit.ft)
        view.save(altitudeString)
    }

    override fun onLoad(altitude: String) {
        altitudeString = altitude
        view.setCalibrationText(altitudeString, unitPref.get())
    }

}