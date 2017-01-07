package testb.org.altimeter.di;

import dagger.Component;
import testb.org.altimeter.view.fragment.CalibrationDialogFragment;
import testb.org.altimeter.view.fragment.CalibrationFragment;
import testb.org.altimeter.view.fragment.DisplayFragment;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {MainActivityPresentersModule.class})
public interface MainActivityComponent {

    void inject(CalibrationFragment calibrationFragment);

    void inject(CalibrationDialogFragment calibrationDialogFragment);

    void inject(DisplayFragment displayFragment);
}
