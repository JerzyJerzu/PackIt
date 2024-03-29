package com.example.packit.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.R;
import com.example.packit.classes.Tag;
import com.example.packit.adapters.TagAdapter;
import com.example.packit.classes.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class InsideTrip extends AppCompatActivity{
    private FloatingActionButton back;
    private Button newItem;
    private Button newRelation;
    private Button DeleteTrip;
    private Button seeAllItemsButton;
    private Button edit;
    private Trip selectedTrip;
    private ListView TagsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_trip);

        selectedTrip = Trip.getTripForID(getIntent().getIntExtra(Trip.TRIP_EDIT_EXTRA, -1));

        initWidgets();
        setOnClickListeners();
        setTagAdapter();
        loadFromDBToMemory();
    }
    private void initWidgets()
    {
        seeAllItemsButton = findViewById(R.id.SeeAllItems);
        TagsListView = findViewById(R.id.TagsList);
        DeleteTrip = findViewById(R.id.deleteTrip);
        back = findViewById(R.id.back2main);
        newItem = findViewById(R.id.NewItemButton);
        newRelation = findViewById(R.id.NewRelationButton);
        edit = findViewById(R.id.editTripButton);
    }
    private void setOnClickListeners()
    {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(InsideTrip.this, EditTrip.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedID);
                startActivity(intent);
            }
        });
        newRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(InsideTrip.this, EditRelation.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedID);
                startActivity(intent);
            }
        });
        seeAllItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(InsideTrip.this, MainItemsList.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedID);
                startActivity(intent);
            }
        });
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                Intent intent = new Intent(InsideTrip.this, EditItem.class);
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
                deleteConfirmaitonDialog();
            }
        });
        TagsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent previousIntent = getIntent();
                Tag selected = (Tag) TagsListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), SeeRelation.class);
                intent.putExtra(Tag.TAG_EDIT_EXTRA, selected.getID());
                int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedTripID);

                Log.d("TAG", "Trip ID passed to relation intent: " + passedTripID);

                startActivity(intent);
            }
        });
    }
    public void OnDelete()
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        dbhelper.deleteTrip(selectedTrip);
        Trip.tripsArrayList.remove(selectedTrip);
        finish();
    }
    private void setTagAdapter()
    {
        TagAdapter adapter = new TagAdapter(getApplicationContext(), selectedTrip.TripTagsArrayList);
        TagsListView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setTagAdapter();
    }
    private void loadFromDBToMemory()
    {
        DatabaseHelper dbHelper = DatabaseHelper.instanceOfDatabase(this);
        dbHelper.populateTripItemsListArray(selectedTrip);
        dbHelper.populateTripTagsArrayList(selectedTrip);
    }
    private void deleteConfirmaitonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OnDelete();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
