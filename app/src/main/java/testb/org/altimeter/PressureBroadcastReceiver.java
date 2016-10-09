package testb.org.altimeter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import testb.org.altimeter.Presenters.BroadcastPresenter;

public class PressureBroadcastReceiver extends BroadcastReceiver {
    private BroadcastPresenter presenter;

    public PressureBroadcastReceiver(){
        throw new IllegalArgumentException("Must pass a presenter");
    }

    public PressureBroadcastReceiver(BroadcastPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        presenter.pressureChanged(intent.getFloatExtra(context.getString(R.string.pref_pressure),0));
    }
}
