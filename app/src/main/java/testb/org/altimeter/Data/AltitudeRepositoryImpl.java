package testb.org.altimeter.Data;


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
    public double getPressureHPA() {
        return sharedPref.getFloat(get(R.string.pref_pressure), Constants.DEFAULT_SEA_PRESSURE); //Default height of 0 given no pressure and calibration
    }

    @Override
    public void setLastKnownPressureHPA(double pressureHPA) {
        sharedPref.edit().putFloat(get(R.string.pref_pressure), (float) pressureHPA).apply();
    }

    @Override
    public void setSeaLevelPressure(double seaLevelPressure) {
        sharedPref.edit().putFloat(get(R.string.sea_level_pressure), 0).apply();
    }

    @Override
    public double getSealevelPressure() {
        return sharedPref.getFloat(get(R.string.sea_level_pressure), 0);
    }

    @Override
    public void resetSeaLevelPressure() {
        sharedPref.edit().putFloat(get(R.string.pref_sea_pressure), Constants.DEFAULT_SEA_PRESSURE).apply();
    }

    @Override
    public int getDistanceUnit() {
        return sharedPref.getInt(get(R.string.pref_unitType), 0);
    }

    private String get(int i) {
        return context.getResources().getString(i);
    }
}
