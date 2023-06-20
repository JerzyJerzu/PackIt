package com.example.packit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemWithTagAdapter extends ArrayAdapter<Item>
{
    public ItemWithTagAdapter(Context context, List<Item> Items)
    {
        super(context, 0, Items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Item item = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trip_cell, parent, false);
        }
        TextView name = convertView.findViewById(R.id.TripName);
        name.setText(item.getName());

        return convertView;
    }
}