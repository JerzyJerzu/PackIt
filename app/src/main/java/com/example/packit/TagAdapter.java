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

public class TagAdapter extends ArrayAdapter<Tag>
{
    public TagAdapter(Context context, List<Tag> tags)
    {
        super(context, 0, tags);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Tag tag = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trip_cell, parent, false);
        }
        TextView name = convertView.findViewById(R.id.TripName);
        name.setText(tag.getName());

        return convertView;
    }
}
