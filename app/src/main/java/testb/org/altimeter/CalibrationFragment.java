package testb.org.altimeter;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalibrationFragment extends Fragment {

    private SharedPreferences sharedPref;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private CalculationSingleton singleton = CalculationSingleton.getInstance();
    public final static double METER_TO_FEET_MULTIPLIER = 3.28084;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.calibration, container, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                updateDistanceText(prev);
                updateBackgroundColor();
            }
        };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);


        distanceTextView = (TextView) myView.findViewById(R.id.distance);
        removeButton = (ImageButton) myView.findViewById(R.id.removeButton);

        if (savedInstanceState == null)
            updateDistanceText("0");
        else {
            updateDistanceText(savedInstanceState.getString("text"));
        }

        Button one = (Button) myView.findViewById(R.id.one);
        Button two = (Button) myView.findViewById(R.id.two);
        Button three = (Button) myView.findViewById(R.id.three);
        Button four = (Button) myView.findViewById(R.id.four);
        Button five = (Button) myView.findViewById(R.id.five);
        Button six = (Button) myView.findViewById(R.id.six);
        Button seven = (Button) myView.findViewById(R.id.seven);
        Button eight = (Button) myView.findViewById(R.id.eight);
        Button nine = (Button) myView.findViewById(R.id.nine);
        Button zero = (Button) myView.findViewById(R.id.zero);
        Button decimal = (Button) myView.findViewById(R.id.decimal);
        Button calibration = (Button) myView.findViewById(R.id.calibrationButton);
        Button[] buttonArr = {one, two, three, four, five, six, seven, eight, nine, zero};
        for (Button button : buttonArr) { //Assigns all the buttons the same listener, we just use the button's numerical text as values
            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    String buttonText = (String) ((Button) v).getText();  //Prevent text from overflowing container
                    if (currentDistance.length() < 8 || (currentDistance.length() < 9 && currentDistance.contains("."))) {
                        if (currentDistance.equals("0")) {
                            updateDistanceText(buttonText);
                        } else {
                            updateDistanceText(currentDistance + buttonText);
                        }
                    }
                }
            });
        }
        removeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                updateDistanceText("0");
            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (currentDistance.length() < 8 && !currentDistance.contains(".")) {
                    if (currentDistance.equals("0")) {
                        updateDistanceText(".");
                    } else {
                        updateDistanceText(currentDistance + ".");
                    }
                }


            }
        });
        calibration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Test to see if the calibration text is "." which creates a NumberFormatException
                try {
                    if (!currentDistance.isEmpty()) { //make sure our string isn't null
                        float qnh = singleton.calculateQNH(Double.valueOf(currentDistance));
                        if (sharedPref.getString("pref_unitType", "0").equals("1")) {
                            singleton.setSealevelPressure(qnh / METER_TO_FEET_MULTIPLIER);
                        } else
                            singleton.setSealevelPressure(qnh);
                    }
                } catch (Exception e) {

                }
            }
        });
        return myView;
    }

    private TextView distanceTextView;
    private ImageButton removeButton;
    private String currentDistance;
    private String prev = "";

    private void updateDistanceText(String distance) {
        int unitTextLength = 1;
        currentDistance = distance;
        prev = distance;
        if (distance.equals("0")) {
            removeButton.setVisibility(View.INVISIBLE);
        } else {
            removeButton.setVisibility(View.VISIBLE);
        }
        if (getUnits().equals("1")) {
            distance = distance + "ft";
            unitTextLength = 2;
        } else { //always fall back to meters
            distance = distance + "m";
            unitTextLength = 1;
        }
        Spannable span = new SpannableString(distance); //Allows us to have smaller unit (m/ft) like we expect instead of same size
        span.setSpan(new RelativeSizeSpan(0.4f), distance.length() - unitTextLength, distance.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        distanceTextView.setText(span);
    }

    //called whenever orientation changes so we can retrieve back from portrait/landscape change
    @Override
    public void onSaveInstanceState(Bundle icicle) {
        icicle.putString("text", currentDistance);
    }


    private String getUnits() {
        return sharedPref.getString("pref_unitType", "0");
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private void updateBackgroundColor() {

    }

}
