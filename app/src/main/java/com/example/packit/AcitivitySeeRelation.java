package com.example.packit;

import android.util.Log;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AcitivitySeeRelation extends AppCompatActivity{

    private FloatingActionButton back;
    private Button edit;
    private Button DeleteTag;
    //private ListView ItemsListView;
    private RecyclerView ItemsRecyclerView;
    private Tag selectedTag;
    private Trip selectedTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_relation);

        ItemsRecyclerView = findViewById(R.id.ItemsWithTagList);
        ItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //ItemsListView = findViewById(R.id.ItemsWithTagList);
        back = findViewById(R.id.back2tripR);
        edit = findViewById(R.id.editRelationButton);
        DeleteTag = findViewById(R.id.deleteTag);

        setOnClickListeners();
        SetSelectedtag();
        setItemsAdapter();
        loadFromDBToMemory();
    }
    private void SetSelectedtag()
    {
        Intent previousIntent = getIntent();

        int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
        int passedTagID = previousIntent.getIntExtra(Tag.TAG_EDIT_EXTRA, -1);

        selectedTrip = Trip.getTripForID(passedTripID);

        Log.d("TAG", "Trip ID value: " + passedTripID);
        Log.d("TAG", "Tag ID value: " + passedTagID);

        //will crash problems when selectedTrip = null
        selectedTag = selectedTrip.getTagForID(passedTagID);
    }
    private void setOnClickListeners()
    {
        DeleteTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDelete(v);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousIntent = getIntent();
                int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                int passedTagID = previousIntent.getIntExtra(Tag.TAG_EDIT_EXTRA, -1);
                Intent intent = new Intent(AcitivitySeeRelation.this, AcitivityEditRelation.class);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedTripID);
                intent.putExtra(Tag.TAG_EDIT_EXTRA, passedTagID);
                startActivity(intent);
            }
        });
    }
    public void setItemsAdapter()
    {/*
        ItemWithTagAdapter itemsAdapter = new ItemWithTagAdapter(getApplicationContext(), selectedTag.TagItemsArrayList);
        ItemsListView.setAdapter(itemsAdapter);
*/
        ItemWithTagAdapter itemsAdapter = new ItemWithTagAdapter(getApplicationContext(), selectedTrip.TripItemsArrayList, selectedTag);
        ItemsRecyclerView.setAdapter(itemsAdapter);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        setItemsAdapter();
    }
    private void loadFromDBToMemory()
    {
        DatabaseHelper dbHelper = DatabaseHelper.instanceOfDatabase(this);
        dbHelper.populateTagItemsArrayList(selectedTag);
    }
    public void OnDelete(View v)
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        dbhelper.deleteTag(selectedTag);
        selectedTrip.TripTagsArrayList.remove(selectedTag);
        finish();
    }
}
