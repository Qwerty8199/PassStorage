package com.example.passstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ViewTheDamnPass extends AppCompatActivity {

    public RecyclerView RealPass;
    public ArrayList<PassClass> PassList;
    public DbHelper db;
    public String CurWebName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DbHelper(this);
        CurWebName = getIntent().getExtras().getString("WebName");
        PassList = db.GetPasswords(CurWebName);
        Log.d(CurWebName,PassList.toString());
        setContentView(R.layout.activity_view_the_damn_pass);
        RecyclerView RealPass = (RecyclerView)findViewById(R.id.realRecycle);
        webPassAdapter PassAdapter = new webPassAdapter(PassList,this);
        RealPass.setLayoutManager(new LinearLayoutManager(this));
        RealPass.setHasFixedSize(true);
        RealPass.setAdapter(PassAdapter);
    }
}