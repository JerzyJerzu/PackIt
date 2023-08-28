package com.example.packit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.R;
import com.example.packit.classes.Trip;
import com.example.packit.adapters.TripAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private ListView TripsListView;
    private FloatingActionButton CreateTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TripsListView = findViewById(R.id.TripsList);
        CreateTrip = findViewById(R.id.CreateTrip);

        setTripAdapter();
        loadFromDBToMemory();
        setOnClickListeners();
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
    private void setOnClickListeners()
    {
        TripsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Trip selectedTrip = (Trip) TripsListView.getItemAtPosition(position);
                Intent editTripIntent = new Intent(getApplicationContext(), InsideTrip.class);
                editTripIntent.putExtra(Trip.TRIP_EDIT_EXTRA, selectedTrip.getID());
                startActivity(editTripIntent);
            }
        });
        CreateTrip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, EditTrip.class);
                startActivity(intent);
            }
        });
    }

}