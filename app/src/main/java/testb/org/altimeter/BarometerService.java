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

public class BarometerService extends Service {
    private Sensor barometer;
    SharedPreferences sharedPref;
    SensorManager sensorManager;
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
        sensorManager.registerListener(new BarometerListener(), barometer, SensorManager.SENSOR_DELAY_UI);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

    }

    class BarometerListener implements SensorEventListener {
        CalculationSingleton calculationSingleton = CalculationSingleton.getInstance();

        @Override
        public void onSensorChanged(SensorEvent event) {
            calculationSingleton.setPressure(event.values[0]); //update pressure from sensor
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //do nothing for now
        }
    }
}
