package com.example.packit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Trip> trips;
    private ListView TripsList;
    private Button CreateTrip;
    private ArrayAdapter<Trip> tripsAdapter;

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
                //additem(view);
            }
        });

        trips = new ArrayList<Trip>();
        //to read about that:
        tripsAdapter = new ArrayAdapter<Trip>(this, android.R.layout.simple_list_item_1);
        TripsList.setAdapter(tripsAdapter);

    }

    private void additem(View view)
    {
        //tripsAdapter.add(new Trip("inrxekqlnqxrvkqlxnvkceqqcqc"));
    }
}