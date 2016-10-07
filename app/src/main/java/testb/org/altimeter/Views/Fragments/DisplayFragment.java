package testb.org.altimeter.Views.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import testb.org.altimeter.R;

public class DisplayFragment extends Fragment implements DisplayView {
    private DisplayPresenter presenter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calibration, container, false);

        unbinder = ButterKnife.bind(this, view);

        // TODO Use fields...
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public class PressureBroadcastReceiver extends BroadcastReceiver{

        public PressureBroadcastReceiver(DisplayPresenter presenter){

        }
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
