package com.example.c1618639.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.c1618639.myapplication.MainActivity.REQUEST_IMAGE_CAPTURE;

public class CameraActivity extends AppCompatActivity {

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

        /*
        Button fab = (Button) findViewById(R.id.take_photo_button);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getLocationAndTakePhoto();
            }
        });
        */

    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void takePhoto(){
        final SharedPreferences sp = this.getSharedPreferences("main_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        int score = sp.getInt("score", 0);
        score++;
        editor.putInt("score", score);
        editor.apply();



//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
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

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(CameraActivity.this, MainActivity.class);
        CameraActivity.this.startActivity(intent);
    }*/

}

