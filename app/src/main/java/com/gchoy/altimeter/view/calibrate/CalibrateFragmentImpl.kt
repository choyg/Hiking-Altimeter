package com.gchoy.altimeter.view.calibrate

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.gchoy.altimeter.Unit
import com.gchoy.altimeter.view.BaseFragmentImpl
import com.gchoy.altimeter.view.BasePresenter
import kotlinx.android.synthetic.main.calibration.*
import testb.org.altimeter.R

class CalibrateFragmentImpl : BaseFragmentImpl(), CalibrateView {

    lateinit var presenter: CalibratePresenter
    private var altitude: String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.calibration, container, false)
        presenter = CalibratePresenterImpl(this, getSharedPref(), getAltimeterService())
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindDigits()
        calibration_clear.setOnClickListener { presenter.clearCalibration() }
        calibration_set.setOnClickListener { presenter.setCalibration() }
        savedInstanceState?.let {
            val saved: String? = it.getString("catsgomoosuperbadkeyname")
            if (saved != null) presenter.onLoad(saved)
        }
    }

    override fun getPresenter(): BasePresenter {
        return presenter
    }

    override fun setCalibrationText(altitude: String, unit: Unit) {
        val span = SpannableString(altitude + unit)
        span.setSpan(RelativeSizeSpan(0.5f), span.length - unit.name.length, span.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        calibration_text.text = span
    }

    override fun save(altitude: String) {
        this.altitude = altitude
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("catsgomoosuperbadkeyname", altitude)
    }

    private fun bindDigits() {
        val listener = DigitListener(presenter)
        val digits = listOf(one, two, three, four, five, six, seven, eight, nine, zero, decimal)
        digits.forEach { it.setOnClickListener(listener) }
    }

    class DigitListener(val presenter: CalibratePresenter) : View.OnClickListener {
        override fun onClick(p0: View?) {
            val button = p0 as Button
            presenter.appendCalibration(button.text[0])
        }
    }

}