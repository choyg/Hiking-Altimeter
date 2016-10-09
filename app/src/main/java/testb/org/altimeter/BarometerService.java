package testb.org.altimeter;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class BarometerService extends Service {
    private Sensor barometer;
    private SharedPreferences sharedPref;
    private SensorManager sensorManager;
    private SensorEventListener barometerListener;
    //TODO Check for service memory leak

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        barometer = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

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
        CalculationSingleton calculationSingleton = CalculationSingleton.getInstance();

        @Override
        public void onSensorChanged(SensorEvent event) {
            sendLocalPressureBroadcast(event.values[0]);
            //calculationSingleton.setPressure(event.values[0]); //update pressure from sensor

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //do nothing for now, can't do much with only 1 sensor :(
        }
    }
}
