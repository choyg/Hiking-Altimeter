package testb.org.altimeter;

import android.app.Application;

import testb.org.altimeter.di.AppComponent;
import testb.org.altimeter.di.AppModule;
import testb.org.altimeter.di.DaggerAppComponent;
import testb.org.altimeter.di.SensorModule;

public class AltimeterApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .sensorModule(new SensorModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
