package com.example.packit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayDeque;
import java.util.ArrayList;

//aaaa

public class MainActivity extends AppCompatActivity {
    private ListView TripsListView;
    private FloatingActionButton CreateTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TripsListView = findViewById(R.id.TripsList);
        CreateTrip = findViewById(R.id.CreateTrip);

        setTripAdapter();
        loadFromDBToMemory();

        CreateTrip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AcitivityEditTrip.class);
                startActivity(intent);
            }
        });
    }
    private void setTripAdapter()
    {
        TripAdapter tripAdapter = new TripAdapter(getApplicationContext(), Trip.tripsArrayList);
        TripsListView.setAdapter(tripAdapter);
    }
    private void loadFromDBToMemory()
    {
        DatabaseHelper dbHelper = DatabaseHelper.instanceOfDatabase(this);
        dbHelper.populateTripsListArray();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setTripAdapter();
    }
}