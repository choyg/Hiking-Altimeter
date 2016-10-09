package testb.org.altimeter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Fragment;


import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.astuetz.tabs.PagerSlidingTabStrip;

import testb.org.altimeter.Views.Fragments.CalibrationFragment;
import testb.org.altimeter.Views.Fragments.DisplayFragment;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private CalculationSingleton singleton = CalculationSingleton.getInstance();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.frame);

        FramePagerAdapter adapter = new FramePagerAdapter(getFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        //Sliding tabs, modified .java file as well
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setBackgroundColor(Color.parseColor("#FF5722"));
        tabs.setTextColor(Color.parseColor("#b3ffffff"));
        tabs.setIndicatorColor(Color.parseColor("#b3ffffff"));
        tabs.setDividerColor(Color.parseColor("#FF5722"));

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar_layout), "No barometer found", Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(Color.parseColor("#FF5722"));
            snackbar.show();
        } else {
            Intent barometerIntent = new Intent(this, BarometerService.class);
            startService(barometerIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public class FramePagerAdapter extends FragmentStatePagerAdapter {

        final String[] TITLES = {"altimeter", "calibration", "settings"};

        public FramePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new DisplayFragment();
                case 1:
                    return new CalibrationFragment();
                case 2:
                    return new SettingsFragment();
                default:
                    Fragment mainDef = new MainFragment();
                    return mainDef;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public String getPageTitle(int position) {
            return TITLES[position];
        }

    }

}
