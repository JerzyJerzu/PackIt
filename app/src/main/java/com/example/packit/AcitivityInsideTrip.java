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

    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_trip);

        back = findViewById(R.id.back2main);
        newItem = findViewById(R.id.NewItemButton);
        newRelation = findViewById(R.id.NewRelationButton);
        edit = findViewById(R.id.editTripButton);

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
    }

}
