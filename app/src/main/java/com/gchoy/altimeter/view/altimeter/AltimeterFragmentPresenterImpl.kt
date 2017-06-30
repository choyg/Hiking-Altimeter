package com.gchoy.altimeter.view.altimeter

import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.gchoy.altimeter.DEFAULT_SEA_PRESSURE
import com.gchoy.altimeter.PREF_CALIBRATION_PRESSURE
import com.gchoy.altimeter.PREF_CALIBRATION_TIME
import com.gchoy.altimeter.service.AltimeterService
import com.gchoy.altimeter.service.Calibration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class AltimeterFragmentPresenterImpl(
        val view: AltimeterFragment,
        val altimeterService: AltimeterService,
        val preferences: RxSharedPreferences
) : AltimeterFragmentPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var previousCalibration: Calibration? = null

    init {
        compositeDisposable.add(altimeterService.getAltitude()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.setAltimeterText(it)
                })
    }

    override fun attachView() {

    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun resetCalibration(calibration: Calibration) {
        previousCalibration = calibration
        preferences.getFloat(PREF_CALIBRATION_PRESSURE).set(DEFAULT_SEA_PRESSURE.toFloat())
        view.showUndoSnackbar()
    }

    override fun undoResetCalibration(): Calibration? {
        val calibration = previousCalibration ?: return null
        preferences.getFloat(PREF_CALIBRATION_PRESSURE).set(calibration.qnh.toFloat())
        preferences.getLong(PREF_CALIBRATION_TIME).set(calibration.date)
    }
}