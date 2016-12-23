package testb.org.altimeter.di;

import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.class)
public class SensorModule {
    @Provides
    @Singleton
    SensorManager provideSensorManager(Application app) {
        return (SensorManager) app.getSystemService(Application.SENSOR_SERVICE);
    }
    @Provides
    @Singleton
    Sensor provideBarometer(SensorManager manager){
        return manager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }
}
