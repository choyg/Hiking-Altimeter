package com.gchoy.altimeter.view.calibrate

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gchoy.altimeter.Unit
import com.gchoy.altimeter.view.BaseFragmentImpl
import com.gchoy.altimeter.view.BasePresenter
import kotlinx.android.synthetic.main.calibration.*
import testb.org.altimeter.R

class CalibrateFragmentImpl : BaseFragmentImpl(), CalibrateView {

    lateinit var presenter: CalibratePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CalibratePresenterImpl(this, getSharedPref(), getAltimeterService())
        return inflater?.inflate(R.layout.calibration, container, false)
    }

    override fun getPresenter(): BasePresenter {
        return presenter
    }

    override fun setCalibrationText(altitude: String, unit: Unit) {
        val span = SpannableString(altitude + unit)
        span.setSpan(RelativeSizeSpan(0.6f), span.length - unit.name.length, span.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        calibration_text.text
    }

}