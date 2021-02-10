package com.example.passstorage;

import android.app.usage.UsageEvents;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class AddPassFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public EditText PassUsername,Passemail,Passpassword,Passwebsite;
    public Button AddButt;
    public SwitchMaterial SwitchSlider;
    public Spinner PassSpinner;
    public ArrayList<String> WebName;
    public DbHelper db;
    public boolean isOn = false;


    private String mParam1;
    private String mParam2;

    public AddPassFragment() {

    }


    public static AddPassFragment newInstance(String param1, String param2) {
        AddPassFragment fragment = new AddPassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = new DbHelper(this.getContext());
        Log.d("init","onCreate");
        WebName = db.GetWebsites();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pass, container, false);
        Passemail = (EditText)view.findViewById(R.id.Passemail);
        PassUsername = (EditText)view.findViewById(R.id.PassUsername);
        Passpassword = (EditText)view.findViewById(R.id.Passpassword);
        Passwebsite = (EditText)view.findViewById(R.id.Passwebsite);
        PassSpinner = (Spinner)view.findViewById(R.id.Passspinner);
        ArrayAdapter<String> WebPassAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line,WebName);
        PassSpinner.setAdapter(WebPassAdapter);
        AddButt = (Button)view.findViewById(R.id.AddButt);
        AddButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = PassUsername.getText().toString();
                String email = Passemail.getText().toString();
                String password = Passpassword.getText().toString();
                String webSite;
                if (isOn) {
                    webSite = Passwebsite.getText().toString();
                } else {
                    webSite = PassSpinner.getSelectedItem().toString();
                }
                if (!UserName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !webSite.isEmpty()) {
                    db.addPassword(webSite, UserName, email, password);
                    Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Enter Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SwitchSlider = (SwitchMaterial)view.findViewById(R.id.switchSlider);
        SwitchSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SwitchSlider.isChecked()){
                    isOn = true;
                    Passwebsite.setVisibility(View.VISIBLE);
                    PassSpinner.setVisibility(View.INVISIBLE);
                }
                else{
                    isOn = false;
                    Passwebsite.setVisibility(View.INVISIBLE);
                    PassSpinner.setVisibility(View.VISIBLE);
                }
            }
        });
        Log.d("init","onCreateView");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("init","onResume");

    }
}