package testb.org.altimeter.di;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import javax.inject.Singleton;

import dagger.Provides;

public class BarometerModule {

    @Provides
    @Singleton
    public SensorManager providesSensorManager(Context context) {
        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    @Provides
    @Singleton
    public Sensor providesBaromteterService(SensorManager sensorManager) {
        return sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }
}
