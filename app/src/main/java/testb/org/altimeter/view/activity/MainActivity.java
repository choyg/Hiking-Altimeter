package testb.org.altimeter.view.activity;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import testb.org.altimeter.AltimeterApplication;
import testb.org.altimeter.BarometerService;
import testb.org.altimeter.R;
import testb.org.altimeter.di.AppComponent;
import testb.org.altimeter.di.DaggerMainActivityComponent;
import testb.org.altimeter.di.MainActivityComponent;
import testb.org.altimeter.view.fragment.CalibrationFragment;
import testb.org.altimeter.view.fragment.DisplayFragment;
import testb.org.altimeter.view.fragment.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    private Intent barometerIntent = null;
    private Unbinder unbinder;
    private MainActivityComponent mainActComponent;
    @BindView(R.id.backgroundImageView)
    ImageView backgroundImageView;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tabTest)
    TabLayout tabLayout;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setTranslucentStatusBar(getWindow());
        setContentView(R.layout.frame);
        unbinder = ButterKnife.bind(this);
        setBackgroundImage("https://source.unsplash.com/collection/140375");

        pager.setAdapter(new FramePagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(pager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF5722")); //TODO: COLORS

        mainActComponent = DaggerMainActivityComponent.builder()
                .appComponent(getApplicationComponent())
                .build();

    }

    public static void setTranslucentStatusBar(Window window) {
        if (window == null) return;
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslucentStatusBarLollipop(window);
        } else if (sdkInt >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatusBarKiKat(window);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void setTranslucentStatusBarLollipop(Window window) {
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void setTranslucentStatusBarKiKat(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar_layout), "No barometer found", Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(Color.parseColor("#FF5722"));
            snackbar.show();
        } else {
            barometerIntent = new Intent(this, BarometerService.class);
            startService(barometerIntent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (barometerIntent != null) {
            stopService(barometerIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "destroy");
        unbinder.unbind();
    }

    public void setBackgroundImage(String url) { //TODO
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(backgroundImageView);
    }

    private AppComponent getApplicationComponent() {
        return ((AltimeterApplication) getApplication()).getAppComponent();
    }

    public MainActivityComponent getActivityComponent() {
        return mainActComponent;
    }

    public class FramePagerAdapter extends FragmentStatePagerAdapter {


        final String[] TITLES = {getString(R.string.tab_title_display),
                getString(R.string.tab_title_calibration),
                getString(R.string.tab_title_settings)};

        FramePagerAdapter(FragmentManager fm) {
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
                    return new DisplayFragment();
            }
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public String getPageTitle(int position) {
            return TITLES[position];
        }

    }

}
