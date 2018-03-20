package com.example.c1618639.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);

        CardView aboutUsCardView = (CardView) findViewById(R.id.card_view_about_us);
        CardView mapCardView = (CardView) findViewById(R.id.card_view_map);
        CardView infoCardView = (CardView) findViewById(R.id.card_view_info);
        CardView cameraCardView = (CardView) findViewById(R.id.card_view_camera);
        CardView galleryCardView = (CardView) findViewById(R.id.card_view_gallery);
        CardView mediaCardView = (CardView) findViewById(R.id.card_view_media);

        /*
        aboutUsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AboutUsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        infoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, InfoActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        cameraCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        galleryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, GalleryActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        mediaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MediaActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        */

        mapCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }

}
