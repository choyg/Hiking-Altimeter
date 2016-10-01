package testb.org.altimeter.Views.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import testb.org.altimeter.R;
import testb.org.altimeter.Views.CalibrationView;

/**
 * Created by testb on 10/1/16.
 */

public class CalibrationFragment extends Fragment implements CalibrationView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calibration, container, false);
        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }

    //Number pad viewlist
    @BindViews({R.id.two})
    List<Button> buttonList;

    @Override
    public void showRemoveButton() {

    }

    @Override
    public void hideRemoveButton() {

    }

    @Override
    public String getCalibrationText() {
        return null;
    }

    @Override
    public String setCalibrationText(CharSequence text) {
        return null;
    }
}
