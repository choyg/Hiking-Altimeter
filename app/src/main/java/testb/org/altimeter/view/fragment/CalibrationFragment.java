package testb.org.altimeter.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import testb.org.altimeter.Constants;
import testb.org.altimeter.R;
import testb.org.altimeter.presenter.CalibrationPresenterImpl;
import testb.org.altimeter.view.CalibrationView;
import testb.org.altimeter.view.activity.MainActivity;


public class CalibrationFragment extends Fragment implements CalibrationView {
    private Unbinder unbinder;

    @Inject
    CalibrationPresenterImpl presenter;

    @BindView(R.id.distance)
    TextView distanceTextView;

    @BindView(R.id.removeButton)
    ImageButton removeButton;

    @BindViews({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    List<Button> numberButtons;

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    public void onNumberButtonClick(Button button) {
        presenter.numberButtonClick(button.getText().toString());
    }

    @OnTextChanged(R.id.distance)
    public void onDistanceTextViewChanged(CharSequence text) {
        presenter.calibrationTextChanged(text.toString());
    }

    @OnClick(R.id.decimal)
    public void onDecimalClicked(Button button) {
        presenter.decimalButtonClick();
    }

    @OnClick(R.id.calibrationButton)
    public void onCalibrationClicked(Button button) {
        presenter.calibrationButtonClick();
    }

    @OnClick(R.id.removeButton)
    public void onRemoveButtonClicked() {
        presenter.deleteButtonClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calibration, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).getApplicationComponent().inject(this);
        String initialVal = "0";
        if (savedInstanceState != null) {
            initialVal = savedInstanceState.getString(getString(R.string.bundle_calibration_key));
        }
        presenter.setView(this, initialVal);
        setCalibrationText(initialVal);
        // TODO Use fields...
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.bundle_calibration_key), presenter.getCalibrationVal());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter = null;
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
        int unitTextLength;
        if (presenter.getUnits() == Constants.Units.FEET) {
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
