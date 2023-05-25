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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_trip);

        back = findViewById(R.id.back2main);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newItem = findViewById(R.id.NewItemButton);

        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcitivityInsideTrip.this, AcitivityEditItem.class);
                startActivity(intent);
            }
        });

        newRelation = findViewById(R.id.NewRelationButton);

        newRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcitivityInsideTrip.this, AcitivityEditRelation.class);
                startActivity(intent);
            }
        });

        edit = findViewById(R.id.editTripButton);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcitivityInsideTrip.this, AcitivityEditTrip.class);
                startActivity(intent);
            }
        });
    }
}
