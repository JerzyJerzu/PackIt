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

public class MainActivity extends AppCompatActivity {

    private ArrayList<Trip> trips;
    private ListView TripsList;
    private ArrayAdapter<Trip> tripsAdapter;
    private FloatingActionButton CreateTrip;

    //@SuppressLint("MissingInflatedId")  //what is this?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TripsList = findViewById(R.id.TripsList);
        CreateTrip = findViewById(R.id.CreateTrip);

        CreateTrip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AcitivityEditTrip.class);
                startActivity(intent);
            }
        });

        trips = new ArrayList<Trip>();
        //to read about that:
        tripsAdapter = new ArrayAdapter<Trip>(this, android.R.layout.simple_list_item_1);
        TripsList.setAdapter(tripsAdapter);
    }

    private void additem(View view)
    {
        setContentView(R.layout.activity_edit_trip);
        tripsAdapter.add(new Trip("inrxekqlnqxrvkqlxnvkceqqcqc"));
    }

}