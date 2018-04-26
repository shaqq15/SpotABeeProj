package com.example.c1618639.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.location.Location;
import android.content.pm.PackageManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, OnCompleteListener<Location> {

    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSION_ACCESS_FINE_LOCATION);
        }else {
            this.mFusedLocationClient.getLastLocation().addOnCompleteListener(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        this.mFusedLocationClient.getLastLocation().addOnCompleteListener(this);
                    }
                } else {
                    showManualLocationDialog();
                }
                return;
            }
        }
    }

    @Override
    public void onComplete(@NonNull Task<Location> task) {
        if(!task.isSuccessful()){
            showManualLocationDialog();
        }else{
            Location l = task.getResult();
            if(l == null){
                showManualLocationDialog();
            }else {
                LatLng detectedLocation = new LatLng(l.getLatitude(), l.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(detectedLocation, 15));
                mMap.addMarker(new MarkerOptions().position(detectedLocation).title("Current Location"));
            }
        }
    }

    private void showManualLocationDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ManualLocationFragment manualLocationFragment = ManualLocationFragment.newInstance();
        manualLocationFragment.show(fm, "fragment_manual_location");

        fm.executePendingTransactions();
        manualLocationFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                final SharedPreferences sp = getSharedPreferences("location_preferences", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sp.getString("manual_location", "");
                LatLng point = gson.fromJson(json, LatLng.class);

                mMap.clear();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
                mMap.addMarker(new MarkerOptions().position(point).title("Current Location"));
            }
        });

    }

}