package com.example.packit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AcitivityInsideTrip extends AppCompatActivity{
    private FloatingActionButton back;
    private Button newItem;
    private Button newRelation;
    private Button DeleteTrip;
    private Button edit;
    private Trip selectedTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_trip);

        DeleteTrip = findViewById(R.id.deleteTrip);
        back = findViewById(R.id.back2main);
        newItem = findViewById(R.id.NewItemButton);
        newRelation = findViewById(R.id.NewRelationButton);
        edit = findViewById(R.id.editTripButton);

        selectedTrip = Trip.getTripForID(getIntent().getIntExtra(Trip.TRIP_EDIT_EXTRA, -1));

        setOnClickListeners();
    }

    private void setOnClickListeners()
    {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(AcitivityInsideTrip.this, AcitivityEditTrip.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedID);
                startActivity(intent);
            }
        });
        newRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(AcitivityInsideTrip.this, AcitivityEditRelation.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedID);
                startActivity(intent);
            }
        });
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(AcitivityInsideTrip.this, AcitivityEditItem.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedID);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DeleteTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDelete(v);
            }
        });
    }
    public void OnDelete(View v)
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        dbhelper.deleteTrip(selectedTrip);
        Trip.tripsArrayList.remove(selectedTrip);
        finish();
    }
}
