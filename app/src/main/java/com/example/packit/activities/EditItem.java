package com.example.packit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.classes.Item;
import com.example.packit.R;
import com.example.packit.classes.Tag;
import com.example.packit.adapters.TagChooseAdapter;
import com.example.packit.classes.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditItem extends AppCompatActivity {
    private Button ApplyItem;
    private Button DeleteItem;
    private FloatingActionButton back2tripI;
    private EditText Editname;
    private EditText EditDescription;
    private Item selectedItem;
    private Trip selectedTrip;
    private RecyclerView TagsRecyclerView;
    private TagChooseAdapter tagsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Editname = findViewById(R.id.ItemNameInput);
        EditDescription = findViewById(R.id.ItemDecription);
        back2tripI = findViewById(R.id.back2trip);
        ApplyItem = findViewById(R.id.applyItem);
        DeleteItem = findViewById(R.id.deleteItem);
        TagsRecyclerView = findViewById(R.id.ItemListOfTag);
        TagsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        else
        {
            DeleteItem.setVisibility(View.INVISIBLE);
        }
    }
    private void setOnClickListeners()
    {
        DeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDelete(v);
            }
        });
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

            Item newItem = new Item(id,TripId,name,description,false);
            selectedTrip.TripItemsArrayList.add(newItem);
            dbhelper.addNewItem(newItem);
            TranscriptFromTagsArrayList2DB(newItem);
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
        tagsAdapter = new TagChooseAdapter(getApplicationContext(), selectedTrip.TripTagsArrayList, selectedItem);
        TagsRecyclerView.setAdapter(tagsAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SetItemChooseTagsAdapter();
    }
    public void OnDelete(View v)
    {
        DatabaseHelper dbhelper = DatabaseHelper.instanceOfDatabase(this);
        dbhelper.deleteItem(selectedItem);
        selectedTrip.TripItemsArrayList.remove(selectedItem);
        finish();
    }
    private void TranscriptFromTagsArrayList2DB(Item newItem)
    {
        DatabaseHelper db = DatabaseHelper.instanceOfDatabase(getApplicationContext());
        for (Tag tag : tagsAdapter.TempSelectedTags) {
            db.addJunction(tag,newItem);
        }
    }
}
