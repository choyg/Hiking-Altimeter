package testb.org.altimeter.Views.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;

import testb.org.altimeter.Data.AltitudeRepositoryImpl;
import testb.org.altimeter.Presenters.CalibrationDialogPresenterImpl;
import testb.org.altimeter.R;
import testb.org.altimeter.Views.CalibrationDialog;


public class CalibrationDialogFragment extends DialogFragment implements CalibrationDialog {
    private CalibrationDialogPresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        presenter = new CalibrationDialogPresenterImpl(this, new AltitudeRepositoryImpl(getActivity()));
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

    public void showResetSnackbar() {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.snackbar_layout), "Calibration Reset", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toastResetActionClicked();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                presenter = null;
            }
        });
        snackbar.getView().setBackgroundColor(Color.parseColor("#FF5722")); //primary orange, TODO find better way to set color
        snackbar.setActionTextColor(Color.parseColor("#b3ffffff")); //primary text dark TODO color management
        snackbar.show();

    }
}
