package testb.org.altimeter.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import testb.org.altimeter.Constants;
import testb.org.altimeter.R;

public class AltitudeRepositoryImpl implements AltitudeRepository {

    private SharedPreferences sharedPref;
    private Context context;

    public AltitudeRepositoryImpl(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    @Override
    public void setPressureHPA(float pressure) {
        sharedPref.edit().putFloat(get(R.string.pref_pressure), pressure).apply();
    }

    @Override
    public float getPressureHPA() {
        //Default height of 0 given no pressure and calibration
        return sharedPref.getFloat(get(R.string.pref_pressure), Constants.DEFAULT_SEA_PRESSURE);
    }

    @Override
    public void setSeaLevelPressure(double seaLevelPressure) {
        sharedPref.edit().putFloat(get(R.string.sea_level_pressure), 0).apply();
    }

    @Override
    public float getUndoCalibration() {
        return sharedPref.getFloat(get(R.string.reset_calibration_temp), Constants.DEFAULT_SEA_PRESSURE);
    }

    @Override
    public float getSealevelPressure() {
        return sharedPref.getFloat(get(R.string.sea_level_pressure), Constants.DEFAULT_SEA_PRESSURE);
    }

    @Override
    public void resetSeaLevelPressure() {
        sharedPref.edit().putFloat(get(R.string.reset_calibration_temp), getSealevelPressure()).apply();
        sharedPref.edit().putFloat(get(R.string.pref_sea_pressure), Constants.DEFAULT_SEA_PRESSURE).apply();

    }

    @Override
    public int getDistanceUnit() {
        return Integer.valueOf(sharedPref.getString(get(R.string.pref_unit_key), String.valueOf(Constants.Units.METERS)));
    }

    private String get(int i) {
        return context.getResources().getString(i);
    }
}
