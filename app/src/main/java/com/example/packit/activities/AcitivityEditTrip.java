package com.example.packit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.R;
import com.example.packit.classes.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AcitivityEditTrip extends AppCompatActivity {
    private Button ApplyTrip;
    private FloatingActionButton back2tripT;
    private EditText Editname;
    private Trip selectedTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        Editname = findViewById(R.id.TripNameInput);
        back2tripT = findViewById(R.id.back2tripT);
        ApplyTrip = findViewById(R.id.applyTrip);

        setOnClickListeners();
        CheckForEditTrip();
    }

    private void CheckForEditTrip()
    {
        Intent previousIntent = getIntent();

        //when not given TRIP_EDIT_EXTRA, will return -1 and getTripForID(passedID) will return null
        int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
        selectedTrip = Trip.getTripForID(passedID);

        if (selectedTrip != null)
        {
            Editname.setText(selectedTrip.toString());
        }
    }

    private void setOnClickListeners()
    {
        ApplyTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnApply(v);
            }
        });
        back2tripT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void OnApply(View v)
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        String name = String.valueOf(Editname.getText());

        if(selectedTrip == null)
        {
            //Are trips in a database sorted by IDs?
            int id;
            if (Trip.tripsArrayList.size() > 0)
            {
                id = Trip.tripsArrayList.get(Trip.tripsArrayList.size() - 1).getID() + 1;
            }
            else
            {
                id = 0;
            }
            Trip newTrip = new Trip(id,name);
            Trip.tripsArrayList.add(newTrip);
            dbhelper.addtrip(newTrip);
        }
        else
        {
            selectedTrip.setName(name);
            dbhelper.updateTripInDB(selectedTrip);
        }
        finish();
    }
}
