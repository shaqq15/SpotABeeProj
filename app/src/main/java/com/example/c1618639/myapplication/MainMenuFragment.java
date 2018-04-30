package com.example.c1618639.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;
import android.content.Intent;


public class MainMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        CardView mapCardView = v.findViewById(R.id.card_view_map);
        mapCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        CardView aboutUsCardView = v.findViewById(R.id.card_view_about_us);
        aboutUsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new AboutUsFragment());
                ft.commit();
                ft.addToBackStack(null);
            }
        });

        CardView infoCardView = v.findViewById(R.id.card_view_info);
        infoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new InfoPageFragment());
                ft.commit();
                ft.addToBackStack(null);
            }
        });

        CardView cameraCardView = v.findViewById(R.id.card_view_camera);
        cameraCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), CameraActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        CardView galleryCardView = v.findViewById(R.id.card_view_imagga);
        galleryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apiIntent = new Intent(getActivity(), API.class);
               getActivity().startActivity(apiIntent);
            }
        });

        CardView mediaCardView = v.findViewById(R.id.card_view_media);
        mediaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new MediaFragment());
                ft.commit();
                ft.addToBackStack(null);
            }
        });


        return v;
    }
}
