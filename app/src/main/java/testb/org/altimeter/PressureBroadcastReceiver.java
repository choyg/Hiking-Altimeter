package testb.org.altimeter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class PressureBroadcastReceiver extends BroadcastReceiver {
    List<PressureListener> listeners = new ArrayList<>();

    public void registerListener(PressureListener listener) {
        listeners.add(listener);
    }

    public void unregister(PressureListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = context.getString(R.string.pref_pressure);

        float value = intent.getExtras().getFloat(key, Constants.DEFAULT_SEA_PRESSURE);
        for (PressureListener listener : listeners) {
            listener.onPressureChanged(value);
        }
    }

    public interface PressureListener {
        void onPressureChanged(float pressure);
    }
}