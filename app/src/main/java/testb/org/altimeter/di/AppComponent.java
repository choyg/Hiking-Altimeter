package testb.org.altimeter.di;

import javax.inject.Singleton;

import dagger.Component;
import testb.org.altimeter.BarometerService;
import testb.org.altimeter.data.AltitudeRepository;
import testb.org.altimeter.view.activity.MainActivity;
import testb.org.altimeter.view.fragment.CalibrationDialogFragment;
import testb.org.altimeter.view.fragment.CalibrationFragment;
import testb.org.altimeter.view.fragment.DisplayFragment;

@Singleton
@Component(modules = {SensorModule.class, DataModule.class})
public interface AppComponent {
    void inject(BarometerService barometerService);

    void inject(MainActivity mainActivity);

    //Expose Repository to dependency graph for our fragments.
    AltitudeRepository altitudeRepository();

}
