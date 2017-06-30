package com.gchoy.altimeter.view.altimeter

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gchoy.altimeter.service.Altitude
import com.gchoy.altimeter.service.Calibration
import com.gchoy.altimeter.view.BaseFragmentImpl
import com.gchoy.altimeter.view.BasePresenter
import kotlinx.android.synthetic.main.altimeter.*
import testb.org.altimeter.R

class AltimeterFragmentImpl : BaseFragmentImpl(), AltimeterFragment {

    lateinit var presenter: AltimeterFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = AltimeterFragmentPresenterImpl(this, getAltimeterService())
        return inflater?.inflate(R.layout.altimeter, container, false)
    }

    override fun getPresenter(): BasePresenter {
        return presenter
    }

    override fun setAltimeterText(altitude: Altitude) {
        val span = SpannableString(altitude.altitude + altitude.units)
        span.setSpan(RelativeSizeSpan(0.6f), span.length - altitude.units.length, span.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        altimeter_textview.text = span
    }

    override fun setCalibration(calibration: Calibration) {

    }

    override fun setCalibrationVisible(boolean: Boolean) {
        altimeter_calibration.visibility = if (boolean) View.VISIBLE else View.GONE
    }
}