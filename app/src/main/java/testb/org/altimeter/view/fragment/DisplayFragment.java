package testb.org.altimeter.view.fragment;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import testb.org.altimeter.Constants;
import testb.org.altimeter.PressureBroadcastReceiver;
import testb.org.altimeter.R;
import testb.org.altimeter.model.Altitude;
import testb.org.altimeter.model.CalibrationModel;
import testb.org.altimeter.presenter.DisplayPresenter;
import testb.org.altimeter.view.DisplayView;
import testb.org.altimeter.view.activity.MainActivity;

public class DisplayFragment extends Fragment implements DisplayView {
    @Inject
    DisplayPresenter presenter;
    private Unbinder unbinder;
    private PressureBroadcastReceiver receiver;
    private Listener listener;

    @BindView(R.id.altitudeText)
    TextView altitudeText;
    @BindView(R.id.calibrationOptions)
    Button calibrationOptions;

    @OnClick(R.id.calibrationOptions)
    public void showCalibrationDialog() {
        CalibrationDialogFragment dialog = new CalibrationDialogFragment();
        dialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        presenter.setView(this);
        listener = new Listener(presenter);
        receiver = new PressureBroadcastReceiver();
        receiver.registerListener(listener);
        // TODO Use fields...
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
        unbinder.unbind();
        receiver.unregister(listener);
        presenter = null;

    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver, new IntentFilter(getString(R.string.intent_filter)));

    }

    @Override
    public void updateElevation(Altitude altitude) {
        int unitTextLength;
        String str = altitude.toString();
        if (altitude.getUnit() == Constants.Units.FEET) {
            unitTextLength = 2;
        } else { //always fall back to meters
            unitTextLength = 1;
        }
        //Allows us to have smaller unit (m/ft) like we expect instead of same size
        Spannable span = new SpannableString(str);
        span.setSpan(new RelativeSizeSpan(0.6f),
                str.length() - unitTextLength,
                str.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        altitudeText.setText(span);
    }

    @Override
    public void updateCalibration(CalibrationModel calibration) {
        //TODO
    }

    private class Listener implements PressureBroadcastReceiver.PressureListener {
        private DisplayPresenter presenter;

        public Listener(DisplayPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onPressureChanged(float pressure) {
            presenter.onPressureChanged(pressure);
        }
    }

}
