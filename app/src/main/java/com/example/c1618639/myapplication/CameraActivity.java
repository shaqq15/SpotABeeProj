package com.example.c1618639.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

public class CameraActivity extends AppCompatActivity implements OnCompleteListener<Location> {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //code adapted from https://developer.android.com/training/camera/photobasics.html#TaskPhotoView

        getLocationAndTakePhoto();

        Button fab = (Button) findViewById(R.id.take_photo_button);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getLocationAndTakePhoto();
            }

        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        this.mFusedLocationClient.getLastLocation().addOnCompleteListener(this);
                    }
                }else{

                }
                return;
            }
        }
    }

    @Override
    public void onComplete(@NonNull Task<Location> task) {
        if(!task.isSuccessful()){
            Toast.makeText(this, getResources().getString(R.string.location_error), Toast.LENGTH_LONG).show();
            showManualLocationDialog();
        }else{
            Location l = task.getResult();
            if(l == null){
                Toast.makeText(this, getResources().getString(R.string.location_error), Toast.LENGTH_LONG).show();
                showManualLocationDialog();
            }else {
                LatLng detectedLocation = new LatLng(l.getLatitude(), l.getLongitude());
                takePhoto(detectedLocation);
            }
        }
    }

    private void getLocationAndTakePhoto(){
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSION_ACCESS_FINE_LOCATION);
        }else {
            this.mFusedLocationClient.getLastLocation().addOnCompleteListener(this);
        }
    }

    private void takePhoto(LatLng location) {
        final SharedPreferences sp = this.getSharedPreferences("main_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        int score = sp.getInt("score", 0);
        score++;
        editor.putInt("score", score);
        editor.apply();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
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
                takePhoto(point);
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
