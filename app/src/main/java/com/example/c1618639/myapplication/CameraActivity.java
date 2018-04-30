package com.example.c1618639.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
       File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        /*File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
        );*/

        File image = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
        //File image = new File(storageDir.getAbsolutePath() + "/" + imageFileName);

        /*File image = new File(
                imageFileName,
                ".jpg",
                storageDir
        );*/
       // Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
       // mediaScanIntent.setData(FileProvider.getUriForFile(getApplicationContext(), "com.example.android.fileprovider", image));
       // this.sendBroadcast(mediaScanIntent);



        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                System.out.println("11111111");
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            System.out.println("AAAAAAAA");

            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            System.out.println(storageDir.listFiles().length);

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                System.out.println("BBBBBBBB");

                galleryAddPic();
                galleryAddPic2(photoURI);
            }
        }
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    private void galleryAddPic2(Uri u) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(u);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //code adapted from https://developer.android.com/training/camera/photobasics.html#TaskPhotoView

        dispatchTakePictureIntent();

        Button fab = (Button) findViewById(R.id.take_photo_button);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dispatchTakePictureIntent();
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
                takePhoto(point);
            }
        });

    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//                        this.mFusedLocationClient.getLastLocation().addOnCompleteListener(this);
//                    }
//                }else{
//                    showManualLocationDialog();
//                }
//            }
//        }
//    }

//    @Override
//    public void onComplete(@NonNull Task<Location> task) {
//        if(!task.isSuccessful()){
//            Toast.makeText(this, getResources().getString(R.string.location_error), Toast.LENGTH_LONG).show();
//            showManualLocationDialog();
//        }else{
//            Location l = task.getResult();
//            if(l == null){
//                Toast.makeText(this, getResources().getString(R.string.location_error), Toast.LENGTH_LONG).show();
//                showManualLocationDialog();
//            }else {
//                LatLng detectedLocation = new LatLng(l.getLatitude(), l.getLongitude());
//                takePhoto(detectedLocation);
//            }
//        }
//    }

//    private void getLocationAndTakePhoto(){
//        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSION_ACCESS_FINE_LOCATION);
//        }else {
//            this.mFusedLocationClient.getLastLocation().addOnCompleteListener(this);
//        }
//    }

    private void takePhoto(LatLng location) {
        final SharedPreferences sp = this.getSharedPreferences("main_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        int score = sp.getInt("score", 0);
        score++;
        editor.putInt("score", score);
        editor.apply();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(CameraActivity.this, MainActivity.class);
        CameraActivity.this.startActivity(intent);
    }
}


