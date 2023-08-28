package com.example.packit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.R;
import com.example.packit.classes.Tag;
import com.example.packit.classes.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AcitivityEditRelation extends AppCompatActivity{
    private Button applyRelation;
    private FloatingActionButton back2tripR;
    private EditText Editname;
    private EditText EditDescription;
    private Tag selectedTag;
    private Trip selectedTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_relation);

        back2tripR = findViewById(R.id.back2relation);
        applyRelation = findViewById(R.id.applyRelation);
        Editname = findViewById(R.id.RelationNameInput);
        EditDescription = findViewById(R.id.Relationdescription);

        setOnClickListeners();
        CheckForEditRelation();
    }
    private void setOnClickListeners()
    {
        applyRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {OnApply(v);}
        });
        back2tripR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void CheckForEditRelation()
    {
        Intent previousIntent = getIntent();

        int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
        int passedRelationID = previousIntent.getIntExtra(Tag.TAG_EDIT_EXTRA, -1);

        selectedTrip = Trip.getTripForID(passedTripID);
        //will crash problems when selectedTrip = null
        selectedTag = selectedTrip.getTagForID(passedRelationID);

        if (selectedTag != null)
        {
            Editname.setText(selectedTag.getName());
            EditDescription.setText(selectedTag.getDescription());
        }
    }
    private void OnApply(View v)
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        String name = String.valueOf(Editname.getText());
        String description = String.valueOf(EditDescription.getText());

        if(selectedTag == null)
        {
            //Are trips in a database sorted by IDs?
            int TripId = selectedTrip.getID();
            int id;
            if (selectedTrip.TripTagsArrayList.size() > 0)
            {
                id = selectedTrip.TripTagsArrayList.get(selectedTrip.TripTagsArrayList.size() - 1).getID() + 1;
            }
            else
            {
                id = 0;
            }
            // may be really dangerous if violates primary key!!!
            id = id + 10000*TripId;

            Tag newTag = new Tag(id,TripId, name,description);
            selectedTrip.TripTagsArrayList.add(newTag);
            dbhelper.addNewTag(newTag);
        }
        else
        {
            selectedTag.setName(name);
            selectedTag.setDescription(description);
            dbhelper.UpdateTagInDB(selectedTag);
        }
        finish();
    }
}
