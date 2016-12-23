package testb.org.altimeter.di;

import javax.inject.Singleton;

import dagger.Component;
import testb.org.altimeter.BarometerService;
import testb.org.altimeter.view.fragment.CalibrationDialogFragment;
import testb.org.altimeter.view.fragment.CalibrationFragment;
import testb.org.altimeter.view.fragment.DisplayFragment;

@Singleton
@Component(modules = {SensorModule.class, DataModule.class})
public interface AppComponent {
    void inject(BarometerService barometerService);

    void inject(CalibrationFragment calibrationFragment);

    void inject(CalibrationDialogFragment calibrationDialogFragment);

    void inject(DisplayFragment displayFragment);


}
