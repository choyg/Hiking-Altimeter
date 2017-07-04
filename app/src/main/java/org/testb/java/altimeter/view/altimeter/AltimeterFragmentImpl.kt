package org.testb.java.altimeter.view.altimeter

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.altimeter.*
import org.testb.Android.R
import org.testb.java.altimeter.service.Altitude
import org.testb.java.altimeter.service.Calibration
import org.testb.java.altimeter.view.BaseFragmentImpl
import org.testb.java.altimeter.view.BasePresenter
import java.util.*

class AltimeterFragmentImpl : BaseFragmentImpl(), AltimeterView {

    lateinit var presenter: AltimeterFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = AltimeterFragmentPresenterImpl(this, getAltimeterService(), getSharedPref())
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
        var string: String = getString(R.string.calibrated_text)
        string += " " + getDate(calibration.date)
        altimeter_calibration.text = string
    }

    override fun setCalibrationVisible(boolean: Boolean) {
        altimeter_calibration.visibility = if (boolean) View.VISIBLE else View.GONE
    }

    override fun showUndoSnackbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun getDate(date: Long): String {
        val dateFormat = android.text.format.DateFormat.getDateFormat(context)
        val dateObj = Date()
        dateObj.time = date
        return dateFormat.format(dateObj)
    }
}