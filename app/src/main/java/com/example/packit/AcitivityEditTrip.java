package com.example.packit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AcitivityEditTrip extends AppCompatActivity {
    private Button ApplyTrip;
    private FloatingActionButton back2tripT;
    private EditText Editname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Editname = findViewById(R.id.TripNameInput);
        back2tripT = findViewById(R.id.back2tripT);
        back2tripT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ApplyTrip = findViewById(R.id.applyTrip);
        ApplyTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyNew(v);
            }
        });
    }
    public void ApplyNew(View v)
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        String name = String.valueOf(Editname.getText());
        int id = Trip.tripsArrayList.size();
        Trip newTrip = new Trip(id,name);
        Trip.tripsArrayList.add(newTrip);
        dbhelper.addtrip(newTrip);
        finish();
    }
}
