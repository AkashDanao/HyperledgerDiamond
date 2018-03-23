package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshay.shah on 23/03/18.
 */

public class DiamondJourneyFragment extends Fragment implements OnMapReadyCallback {
    private List<Record> listRecords;
    private SupportMapFragment supportMapFragment;
    private LatLngBounds latLngBounds;
    private LatLngBounds.Builder latLngBoundsBuilder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diamond_journey, null);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.supportMapFragment);
        supportMapFragment.getMapAsync(this);
        return view;
    }

    public void setListRecords(List<Record> listRecords) {
        this.listRecords = listRecords;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        ArrayList<LatLng> points = null;
        if (listRecords != null && listRecords.size() > 1) {
            PolylineOptions polyLineOptions = getPolyLineOptions(listRecords);
            getMarkerOptions(googleMap, listRecords);
            googleMap.addPolyline(polyLineOptions);
            latLngBounds = latLngBoundsBuilder.build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds, 100);
            googleMap.setOnMapLoadedCallback(() -> googleMap.animateCamera(cameraUpdate));
        }

    }

    private void getMarkerOptions(GoogleMap googleMap, List<Record> listRecords) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Double.parseDouble(listRecords.get(0).getLatitude()), Double.parseDouble(listRecords.get(0).getLongitude())));
        markerOptions.title("Miner");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(markerOptions);
        latLngBoundsBuilder.include(markerOptions.getPosition());
        markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Double.parseDouble(listRecords.get(2).getLatitude()), Double.parseDouble(listRecords.get(2).getLongitude())));
        markerOptions.title("Transporter");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        googleMap.addMarker(markerOptions);
        latLngBoundsBuilder.include(markerOptions.getPosition());
        markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Double.parseDouble(listRecords.get(listRecords.size() - 1).getLatitude()), Double.parseDouble(listRecords.get(listRecords.size() - 1).getLongitude())));
        markerOptions.title("Dealer");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        googleMap.addMarker(markerOptions);
        latLngBoundsBuilder.include(markerOptions.getPosition());
    }

    private PolylineOptions getPolyLineOptions(List<Record> listRecords) {
        PolylineOptions polyLineOptions = new PolylineOptions();
        latLngBoundsBuilder = new LatLngBounds.Builder();
        List points = new ArrayList<LatLng>();
        for (int i = 0; i < listRecords.size(); i++) {
            LatLng position = new LatLng(Double.parseDouble(listRecords.get(i).getLatitude()), Double.parseDouble(listRecords.get(i).getLongitude()));
            points.add(position);
            latLngBoundsBuilder.include(position);
        }
        polyLineOptions.addAll(points);
        polyLineOptions.width(5);
        polyLineOptions.color(Color.BLUE);
        return polyLineOptions;
    }
}
