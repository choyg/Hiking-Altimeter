package testb.org.altimeter.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.frame.*
import testb.org.altimeter.R
import testb.org.altimeter.view.altimeter.AltimeterFragmentImpl
import testb.org.altimeter.view.calibrate.CalibrateFragmentImpl
import testb.org.altimeter.view.settings.SettingsFragmentImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame)
        //setup sliding tabs
        frame_pager.adapter = MainPagerAdapter(this, supportFragmentManager)
        frame_tabs.setupWithViewPager(frame_pager)
    }

    private class MainPagerAdapter(context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val titles: Array<String> = context.resources.getStringArray(R.array.main_tabs)

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> AltimeterFragmentImpl()
                1 -> CalibrateFragmentImpl()
                2 -> SettingsFragmentImpl()
                else -> AltimeterFragmentImpl()
            }
        }

        override fun getCount(): Int {
            return titles.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }
    }
}