package com.example.c1618639.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by c1736285 on 23/04/2018.
 */

//public class ImageAdapter extends BaseAdapter {
//    private Context mContext;
//
//    private File[] listOfExternalStorageFiles;
//
//    public ImageAdapter(Context c) {
//        mContext = c;
//
//        File extStorage = Environment.getExternalStorageDirectory();
//        listOfExternalStorageFiles = extStorage.listFiles();

        //loop over




//    }
//
//    public int getCount() {
//        return listOfExternalStorageFiles.length;
//    }
//
//    public Object getItem(int position) {
//        return listOfExternalStorageFiles[position];
//    }
//
//    public long getItemId(int position) {
//        return position;
//    }

    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85)); //change this
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }

       // //imageView.setImageResource(mThumbIds[position]);

//        imageView.setImageURI(Uri.fromFile(listOfExternalStorageFiles[position]));
//        return imageView;
//    }

    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7
   // };
//}
