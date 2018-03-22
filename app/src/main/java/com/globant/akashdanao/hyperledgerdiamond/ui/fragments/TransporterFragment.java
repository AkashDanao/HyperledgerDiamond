package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.tracker.DiamondLocationTracker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransporterFragment extends Fragment {

    private DiamondLocationTracker tracker;
    private boolean trackerBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            tracker = ((DiamondLocationTracker.DiamondLocationTrackerBinder) service).getService();

            toggleButtonState();

            trackerBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            tracker = null;
        }
    };

    @BindView(R.id.buttonStartTracking)
    Button buttonStartTracking;
    @BindView(R.id.diamondId)
    EditText diamondId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tracking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent intent = new Intent(getContext(), DiamondLocationTracker.class);
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();

        getActivity().unbindService(connection);
    }

    @OnClick(R.id.buttonStartTracking)
    public void onClick() {
        if (trackerBound) {
            if (tracker.isLocationBeingTracked()) {
                tracker.stopLocationUpdates();
            } else {
                getActivity().startService(new Intent(getContext(), DiamondLocationTracker.class));
                tracker.startLocationUpdates(diamondId.getText().toString());
            }

            toggleButtonState();
        }
    }

    private void toggleButtonState() {
        buttonStartTracking.setText(tracker.isLocationBeingTracked() ? "Stop Tracking" : "Start Tracking");
    }
}