package com.example.passstorage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ViewPassFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public RecyclerView RecyclerPassView;
    public DbHelper db;
    public ArrayList<String> webName;
    
    private String mParam1;
    private String mParam2;

    public ViewPassFragment() {
        // Required empty public constructor
    }


    public static ViewPassFragment newInstance(String param1, String param2) {
        ViewPassFragment fragment = new ViewPassFragment();
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
        webName = db.GetWebsites();
        Log.d("onCreate",webName.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pass, container, false);
        RecyclerPassView = (RecyclerView)view.findViewById(R.id.RecyclerPassView);
        webNameAdapter webAdapter = new webNameAdapter(webName,getContext());
        RecyclerPassView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RecyclerPassView.setAdapter(webAdapter);
        return view;
    }
}