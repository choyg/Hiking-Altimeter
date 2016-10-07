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

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepositoryImpl;
import testb.org.altimeter.Presenters.CalibrationPresenter;
import testb.org.altimeter.Presenters.CalibrationPresenterImpl;
import testb.org.altimeter.R;
import testb.org.altimeter.Views.CalibrationView;

/**
 * Created by testb on 10/1/16.
 */

public class CalibrationFragment extends Fragment implements CalibrationView {
    private Unbinder unbinder;
    private CalibrationPresenter presenter;

    @BindView(R.id.distance)
    TextView distanceTextView;

    @BindView(R.id.removeButton)
    Button removeButton;

    @BindViews({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    List<Button> numberButtons;

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    public void onNumberButtonClick(Button button) {
        presenter.numberButtonClick(button.getText().toString());
    }

    @OnClick(R.id.decimal)
    public void onDecimalClicked(Button button) {
        presenter.decimalButtonClick();
    }

    @OnClick(R.id.calibrationButton)
    public void onCalibrationClicked(Button button) {
        presenter.calibrationButtonClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calibration, container, false);
        presenter = new CalibrationPresenterImpl(this, new AltitudeRepositoryImpl(getActivity()));
        unbinder = ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRemoveButton() {
        removeButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRemoveButton() {
        removeButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public String getCalibrationText() {
        return distanceTextView.getText().toString();
    }

    @Override
    public void setCalibrationText(String text) {
        int unitTextLength = 1;
        if (presenter.getUnits() == Constants.UNITS_FEET) {
            text = text + "ft";
            unitTextLength = 2;
        } else { //always fall back to meters
            text = text + "m";
            unitTextLength = 1;
        }
        Spannable span = new SpannableString(text); //Allows us to have smaller unit (m/ft) like we expect instead of same size
        span.setSpan(new RelativeSizeSpan(0.4f), text.length() - unitTextLength, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        distanceTextView.setText(span);
    }
}
