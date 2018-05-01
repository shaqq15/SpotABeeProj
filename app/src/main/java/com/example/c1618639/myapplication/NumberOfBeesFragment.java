package com.example.c1618639.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import android.content.SharedPreferences;

public class NumberOfBeesFragment extends DialogFragment {

    public NumberOfBeesFragment() { }
    private Button saveButton;
    private Spinner numberOfBeesSpinner;
    private EditText description;


    public static NumberOfBeesFragment newInstance() {
        return new NumberOfBeesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_number_of_bees, container);

        Spinner dropdown = v.findViewById(R.id.number_picker);
        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        saveButton = (Button) v.findViewById(R.id.number_of_bees_confirm_button);
        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences sp = getActivity().getSharedPreferences("number_of_bees", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sp.edit();

                description = (EditText) v.findViewById(R.id.description_input);

                /* why the fuck is this null
                Spinner numberOfBeesSpinner = (Spinner) v.findViewById(R.id.number_picker);
                String numberOfBees = numberOfBeesSpinner.getSelectedItem().toString();

                editor.putString("number_of_bees", "1");
                editor.putString("description", description.getText().toString());
                editor.apply();*/
                dismiss();
            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}
