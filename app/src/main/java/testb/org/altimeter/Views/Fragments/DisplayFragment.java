package testb.org.altimeter.Views.Fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import testb.org.altimeter.Data.AltitudeRepositoryImpl;
import testb.org.altimeter.Model.CalibrationModel;
import testb.org.altimeter.Presenters.DisplayPresenter;
import testb.org.altimeter.Presenters.DisplayPresenterImpl;
import testb.org.altimeter.PressureBroadcastReceiver;
import testb.org.altimeter.R;
import testb.org.altimeter.Views.DisplayView;

public class DisplayFragment extends Fragment implements DisplayView, CalibrationDialogCallback{
    //private DisplayPresenter presenter;
    private Unbinder unbinder;
    private PressureBroadcastReceiver receiver;
    private DisplayPresenter presenter;

    @BindView(R.id.altitudeText)
    TextView altitudeText;
    @BindView(R.id.calibrationOptions)
    Button calibrationOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new DisplayPresenterImpl(new AltitudeRepositoryImpl(getActivity()));
        //receiver = new PressureBroadcastReceiver(presenter);
        // TODO Use fields...
        return view;
    }

    @OnClick(R.id.calibrationOptions)
    public void showCalibrationDialog() {
        CalibrationDialogFragment dialog = new CalibrationDialogFragment();
        dialog.show(getFragmentManager(), "dialog");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
        unbinder.unbind();
        presenter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        //LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver, new IntentFilter(getString(R.string.intent_filter)));
    }

    @Override
    public void updateElevation(String text) { //TODO
        int unitTextLength;
        String str = text;
        //str = String.format(getStrFormat(), text);
        if ("1".equals("1")) {
            str = str + "ft";
            unitTextLength = 2;
        } else { //always fall back to meters
            str = str + "m";
            unitTextLength = 1;
        }
        Spannable span = new SpannableString(str); //Allows us to have smaller unit (m/ft) like we expect instead of same size
        span.setSpan(new RelativeSizeSpan(0.6f), str.length() - unitTextLength, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        altitudeText.setText(span);
    }

    @Override
    public void updateCalibration(CalibrationModel calibration) {
        //TODO
    }

    @Override
    public void resetButtonClicked() {
        presenter.dialogResetButtonClicked();
    }

    @Override
    public void cancelButtonClicked() {
        presenter.dialogCancelButtonClicked();
    }
}
