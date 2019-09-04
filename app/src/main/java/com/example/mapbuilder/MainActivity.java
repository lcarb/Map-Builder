package com.example.mapbuilder;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        MapFragment frag = (MapFragment) fm.findFragmentById(
                R.id.frag_map);
        if(frag == null) // It might already be there!
        {
            frag = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.frag_map, frag)
                    .commit();
        }



    }
}
