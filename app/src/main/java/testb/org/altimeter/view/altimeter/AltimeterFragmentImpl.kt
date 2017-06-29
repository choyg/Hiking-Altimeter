package testb.org.altimeter.view.altimeter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import testb.org.altimeter.R
import testb.org.altimeter.view.BaseFragmentImpl
import testb.org.altimeter.view.BasePresenter

class AltimeterFragmentImpl : BaseFragmentImpl() {
    lateinit var presenter: AltimeterFragmentPresenter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.display, container, false)
    }

    override fun getPresenter(): BasePresenter {
        return presenter
    }

}
