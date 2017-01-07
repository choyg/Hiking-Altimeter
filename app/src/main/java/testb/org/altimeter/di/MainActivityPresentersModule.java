package testb.org.altimeter.di;

import dagger.Binds;
import dagger.Module;
import testb.org.altimeter.presenter.CalibrationDialogPresenter;
import testb.org.altimeter.presenter.CalibrationDialogPresenterImpl;
import testb.org.altimeter.presenter.CalibrationPresenter;
import testb.org.altimeter.presenter.CalibrationPresenterImpl;
import testb.org.altimeter.presenter.DisplayPresenter;
import testb.org.altimeter.presenter.DisplayPresenterImpl;

@Module
public abstract class MainActivityPresentersModule {
    @Binds
    public abstract CalibrationDialogPresenter bindCalibrationDialogPresenter(CalibrationDialogPresenterImpl presenter);

    @Binds
    public abstract CalibrationPresenter bindCalibrationPresenter(CalibrationPresenterImpl presenter);

    @Binds
    public abstract DisplayPresenter bindDisplayPresenter(DisplayPresenterImpl presenter);


}
