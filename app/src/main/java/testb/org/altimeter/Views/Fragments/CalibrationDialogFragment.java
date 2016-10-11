package testb.org.altimeter.Views.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import testb.org.altimeter.Data.AltitudeRepositoryImpl;
import testb.org.altimeter.Presenters.CalibrationDialogPresenter;
import testb.org.altimeter.Presenters.CalibrationDialogPresenterImpl;
import testb.org.altimeter.Presenters.DisplayPresenterImpl;
import testb.org.altimeter.R;


public class CalibrationDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final CalibrationDialogPresenterImpl presenter = new CalibrationDialogPresenterImpl(new AltitudeRepositoryImpl(getActivity()));

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_last_calibration, null);
        return new AlertDialog.Builder(getActivity())
                .setTitle("Last Calibration")
                .setView(v)
                .setPositiveButton("Reset Calibration",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                presenter.dialogResetButtonClicked();
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                presenter.dialogCancelButtonClicked();
                            }
                        }
                )
                .create();
    }
}
