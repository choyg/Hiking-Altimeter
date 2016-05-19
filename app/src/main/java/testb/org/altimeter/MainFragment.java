package testb.org.altimeter;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor barometer;
    private TextView altitudeTextView;
    private CalculationSingleton calculationSingleton = CalculationSingleton.getInstance();

    private final static String PREF_UNIT_METER = "0";
    private final static String PREF_UNIT_FEET = "1";
    public final static double METER_TO_FEET_MULTIPLIER = 3.28084;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.main, container, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        barometer = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);


        altitudeTextView = (TextView) myView.findViewById(R.id.altitudeText);
        if(savedInstanceState==null)
        updateAltitudeTextView(0);
        else
        updateAltitudeTextView(savedInstanceState.getDouble("altitude",0));
        //altitudeTextView.setText(calculateAltitude());


        //thermometer = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (barometer == null) {
            Toast.makeText(getActivity().getApplicationContext(), "Missing Barometer", Toast.LENGTH_LONG).show();

        }
        return myView;
    }
    private double alt;
    public void onSensorChanged(SensorEvent event) {
        if (barometer == event.sensor) {
            double pressureHPA = event.values[0];
            calculationSingleton.setPressure(pressureHPA);
            alt = calculationSingleton.calculateAltitude();
            if(getUnits().equals("1"))
                alt*=METER_TO_FEET_MULTIPLIER;
            updateAltitudeTextView(alt);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, barometer, 500000);

//        sensorManager.registerListener(this, thermometer, 500000);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //Preference Checking
    private SharedPreferences sharedPref;
    private String getUnits(){
        return sharedPref.getString("pref_unitType","3");
    }
    private String getStrFormat(){
        return sharedPref.getString("pref_decimal_places","%.0f");
    }
    @Override
    public void onSaveInstanceState(Bundle icicle) {
        icicle.putDouble("altitude", alt);
    }

    private void updateAltitudeTextView(double alt){
        int unitTextLength=1;
        String str;
        str = String.format(getStrFormat(), alt);
        if(getUnits().equals("1")){
            str=str+"ft";
            unitTextLength=2;
        }
        else { //always fall back to meters
            str = str + "m";
            unitTextLength=1;
        }
        Spannable span = new SpannableString(str); //Allows us to have smaller unit (m/ft) like we expect instead of same size
        span.setSpan(new RelativeSizeSpan(0.6f), str.length() - unitTextLength, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        altitudeTextView.setText(span);
    }
}
