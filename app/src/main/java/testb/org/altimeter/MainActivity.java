package testb.org.altimeter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;


import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.astuetz.tabs.PagerSlidingTabStrip;


public class MainActivity extends FragmentActivity {

    private SharedPreferences sharedPref;
    private CalculationSingleton singleton = CalculationSingleton.getInstance();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

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

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        Intent barometerIntent = new Intent(this, BarometerService.class);
        startService(barometerIntent);

    }



    @Override
    protected void onResume() {
        super.onResume();
        singleton.setSealevelPressure(sharedPref.getFloat("sea_level_pressure",singleton.getSeaLevelPressure()));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("sea_level_pressure", singleton.getSeaLevelPressure());
        editor.apply();
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
                    Fragment main = new MainFragment();
                    return main;
                case 1:
                    Fragment calib = new CalibrationFragment();
                    return calib;
                case 2:
                    Fragment settings = new SettingsFragment();
                    return settings;
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
