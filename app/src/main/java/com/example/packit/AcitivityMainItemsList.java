package com.example.packit;

import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class AcitivityMainItemsList extends AppCompatActivity{

    private FloatingActionButton back;
    private RecyclerView ItemsListView;
    private Trip selectedTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_items);

        ItemsListView = findViewById(R.id.MainItemsList);
        ItemsListView.setLayoutManager(new LinearLayoutManager(this));
        back = findViewById(R.id.back2tripItemsList);

        setOnClickListeners();
        SetSelectedtrip();
        setItemsAdapter();
        loadFromDBToMemory();
    }
    private void SetSelectedtrip()
    {
        Intent previousIntent = getIntent();
        int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
        selectedTrip = Trip.getTripForID(passedTripID);
    }
    private void setOnClickListeners()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*
        ItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent previousIntent = getIntent();
                Item selected = (Item) ItemsListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), AcitivityEditItem.class);
                intent.putExtra(Item.ITEM_EDIT_EXTRA, selected.getID());
                int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
                intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedTripID);

                Log.d("TAG", "Trip ID passed to relation intent: " + passedTripID);

                startActivity(intent);
            }
        });*/
    }
    private void setItemsAdapter()
    {
        ItemCheckAdapter itemsAdapter = new ItemCheckAdapter(getApplicationContext(), selectedTrip.TripItemsArrayList);
        ItemsListView.setAdapter(itemsAdapter);
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
        dbHelper.populateTripItemsListArray(selectedTrip);
    }
}
