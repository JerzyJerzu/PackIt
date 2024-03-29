package com.example.packit.activities;

import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.classes.Item;
import com.example.packit.adapters.ItemCheckAdapter;
import com.example.packit.R;
import com.example.packit.views_helpers.RecyclerItemTouchHelper;
import com.example.packit.classes.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class MainItemsList extends AppCompatActivity{

    private FloatingActionButton back;
    private RecyclerView itemsRecyclerView;
    private Trip selectedTrip;
    private ItemCheckAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_items);

        itemsRecyclerView = findViewById(R.id.MainItemsList);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        //run the edit activity
        /*
        itemsRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent previousIntent = getIntent();
                Item selected = (Item) itemsRecyclerView.getItemAtPosition(position);
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
        itemsAdapter = new ItemCheckAdapter(getApplicationContext(), selectedTrip.TripItemsArrayList);
        itemsRecyclerView.setAdapter(itemsAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(itemsAdapter,this));
        itemTouchHelper.attachToRecyclerView(itemsRecyclerView);

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
    public void moveToEditItem(int position)
    {
        Intent previousIntent = getIntent();
        Item selected = (Item) itemsAdapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), EditItem.class);
        intent.putExtra(Item.ITEM_EDIT_EXTRA, selected.getID());
        int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
        intent.putExtra(Trip.TRIP_EDIT_EXTRA, passedTripID);

        Log.d("TAG", "Trip ID passed to relation intent: " + passedTripID);

        startActivity(intent);
    }
}
