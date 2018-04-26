package com.example.c1618639.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import android.content.SharedPreferences;

public class ManualLocationFragment extends DialogFragment implements GoogleMap.OnMapClickListener, OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    private GoogleMap mMap;
    private TextView mTapTextView;
    private Button manualLocationConfirmButton;

    public ManualLocationFragment() { }

    public static ManualLocationFragment newInstance() {
        return new ManualLocationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manual_location, container);
        mTapTextView = (TextView) v.findViewById(R.id.tap_text);
        manualLocationConfirmButton = (Button) v.findViewById(R.id.manual_location_confirm_button);
        manualLocationConfirmButton.setEnabled(false);
        manualLocationConfirmButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        supportMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.manualLocationMap);

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng point) {

        final SharedPreferences sp = getActivity().getSharedPreferences("location_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        Gson gson = new Gson();
        String json = gson.toJson(point);
        editor.putString("manual_location", json);
        editor.apply();


        manualLocationConfirmButton.setEnabled(true);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(point).title("Selected Location"));
        mTapTextView.setText(getResources().getString(R.string.manual_location_pinned));
    }
}
