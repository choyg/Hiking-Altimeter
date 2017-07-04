package org.testb.altimeter.view.settings

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import org.testb.Android.R
import org.testb.java.altimeter.DEFAULT_SEA_PRESSURE
import org.testb.java.altimeter.PREF_CALIBRATION_PRESSURE
import org.testb.java.altimeter.PREF_CALIBRATION_TIME
import org.testb.java.altimeter.view.MainActivity

class SettingsFragmentImpl : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences)
        PreferenceManager.setDefaultValues(activity, R.xml.preferences, false)

        val preference = (activity as MainActivity).getSharedPref()
        val calibrationPresurePref = preference.getFloat(PREF_CALIBRATION_PRESSURE)
        val calibrationTimepref = preference.getLong(PREF_CALIBRATION_TIME)

        val resetButton = findPreference(getString(R.string.pref_reset))
        resetButton.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            calibrationPresurePref.set(DEFAULT_SEA_PRESSURE.toFloat())
            calibrationTimepref.set(0)
            true
        }
    }

}