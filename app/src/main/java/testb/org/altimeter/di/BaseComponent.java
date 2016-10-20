package testb.org.altimeter.di;

import android.content.SharedPreferences;
import android.hardware.Sensor;

import dagger.Component;
import testb.org.altimeter.MainActivity;

@Component(modules = {BarometerModule.class, PrefModule.class}, dependencies = ApplicationComponent.class)
public interface BaseComponent {

    Sensor getBarometer();

    SharedPreferences getSharedPref();

    void inject(MainActivity activity);
}
