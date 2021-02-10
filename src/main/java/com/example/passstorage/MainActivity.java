package com.example.passstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomAppBar;
    FloatingActionButton fbut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFrag(new ViewPassFragment());
        bottomAppBar = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomAppBar.setOnNavigationItemSelectedListener(this);
        fbut = (FloatingActionButton) findViewById(R.id.fab);

        fbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Frag_container, new AddPassFragment())
                        .commit();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.view:
                fragment = new ViewPassFragment();
                break;

            case R.id.settings:
                fragment = new SettingsFragment();
                break;
        }

        return loadFrag(fragment);
    }


    public boolean loadFrag(Fragment frag){
        if(frag != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.Frag_container, frag)
                    .commit();
            return true;
        }
        return false;
    }



}