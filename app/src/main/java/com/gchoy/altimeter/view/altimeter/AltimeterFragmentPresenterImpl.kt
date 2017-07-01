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
        val view: AltimeterView,
        val altimeterService: AltimeterService,
        val preferences: RxSharedPreferences
) : AltimeterFragmentPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var previousCalibration: Calibration? = null
    private val prefCalibrationPressure = preferences.getFloat(PREF_CALIBRATION_PRESSURE, DEFAULT_SEA_PRESSURE.toFloat())
    private val prefCalibrationDate = preferences.getLong(PREF_CALIBRATION_TIME, 0)

    init {
        compositeDisposable.add(altimeterService.getAltitude()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.setAltimeterText(it)
                })
    }

    override fun attachView() {
        compositeDisposable.add(prefCalibrationDate
                .asObservable()
                .subscribe {
                    if (prefCalibrationDate.get() == 0L) {
                        view.setCalibrationVisible(false)
                    } else {
                        view.setCalibrationVisible(true)
                        view.setCalibration(Calibration(prefCalibrationDate.get(), prefCalibrationPressure.get().toDouble()))
                    }
                })
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun resetCalibration(calibration: Calibration) {
        previousCalibration = calibration
        prefCalibrationPressure.set(DEFAULT_SEA_PRESSURE.toFloat())
        prefCalibrationDate.set(0)
        view.showUndoSnackbar()
    }

    override fun undoResetCalibration(): Calibration? {
        val calibration = previousCalibration ?: return null
        prefCalibrationPressure.set(calibration.qnh.toFloat())
        prefCalibrationDate.set(calibration.date)
        return calibration
    }
}