package com.example.packit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AcitivityEditItem extends AppCompatActivity {
    private Button ApplyItem;
    private FloatingActionButton back2tripI;
    private EditText Editname;
    private EditText EditDescription;
    private Item selectedItem;
    private Trip selectedTrip;
    private ListView TagsListView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        TagsListView = findViewById(R.id.ItemListOfTag);
        Editname = findViewById(R.id.ItemNameInput);
        EditDescription = findViewById(R.id.ItemDecription);
        back2tripI = findViewById(R.id.back2trip);
        ApplyItem = findViewById(R.id.applyItem);

        setOnClickListeners();
        CheckForEditItem();
        SetItemChooseTagsAdapter();
    }
    private void CheckForEditItem()
    {
        Intent previousIntent = getIntent();

        int passedTripID = previousIntent.getIntExtra(Trip.TRIP_EDIT_EXTRA, -1);
        int passedItemID = previousIntent.getIntExtra(Item.ITEM_EDIT_EXTRA, -1);

        selectedTrip = Trip.getTripForID(passedTripID);
        //will crash problems when selectedTrip = null
        selectedItem = selectedTrip.getItemForID(passedItemID);

        if (selectedItem != null)
        {
            Editname.setText(selectedItem.getName());
            EditDescription.setText(selectedItem.getDescription());
        }
    }
    private void setOnClickListeners()
    {
        ApplyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {OnApply(v);}
        });
        back2tripI.setOnClickListener(new View.OnClickListener() {
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
        String description = String.valueOf(EditDescription.getText());

        if(selectedItem == null)
        {
            //Are trips in a database sorted by IDs?
            int TripId = selectedTrip.getID();
            int id;
            if (selectedTrip.TripItemsArrayList.size() > 0)
            {
                id = selectedTrip.TripItemsArrayList.get(selectedTrip.TripItemsArrayList.size() - 1).getID() + 1;
            }
            else
            {
                id = 0;
            }
            // may be really dangerous if violates primary key!!!
            id = id + 10000*TripId;

            Item newItem = new Item(id,name,description,false);
            selectedTrip.TripItemsArrayList.add(newItem);
            dbhelper.addNewItem(newItem);
        }
        else
        {
            selectedItem.setName(name);
            selectedItem.setDescription(description);
            dbhelper.UpdateItemInDB(selectedItem);
        }
        finish();
    }
    private void SetItemChooseTagsAdapter()
    {
        ItemChooseTagsAdapter adapter = new ItemChooseTagsAdapter(getApplicationContext(), selectedTrip.TripTagsArrayList);
        TagsListView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SetItemChooseTagsAdapter();
    }
}
