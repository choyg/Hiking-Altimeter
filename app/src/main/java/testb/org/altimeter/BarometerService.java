package testb.org.altimeter;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Inject;

import testb.org.altimeter.data.AltitudeRepository;

public class BarometerService extends Service {
    @Inject
    Sensor barometer;
    @Inject
    SensorManager sensorManager;
    @Inject
    AltitudeRepository repository;
    private SensorEventListener barometerListener;
    //TODO Check for service memory leak

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        ((AltimeterApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        barometerListener = new BarometerListener();
        sensorManager.registerListener(barometerListener, barometer, SensorManager.SENSOR_DELAY_UI);
        return START_STICKY;
    }

    private void sendLocalPressureBroadcast(float value) {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.pref_pressure), value);
        broadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(barometerListener);
    }

    private class BarometerListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float pressure = event.values[0];
            repository.setPressureHPA(pressure);
            sendLocalPressureBroadcast(pressure);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //do nothing for now, can't do much with only 1 sensor :(
        }
    }
}
