package com.gchoy.altimeter.view.calibrate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gchoy.altimeter.view.BaseFragmentImpl
import com.gchoy.altimeter.view.BasePresenter
import testb.org.altimeter.R

class CalibrateFragmentImpl : BaseFragmentImpl(), CalibrateView {

    lateinit var presenter: CalibratePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.calibration, container, false)
    }

    override fun getPresenter(): BasePresenter {
        return presenter
    }
}