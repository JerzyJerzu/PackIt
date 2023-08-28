package com.example.packit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.packit.R;
import com.example.packit.classes.Trip;

import java.util.List;

public class TripAdapter extends ArrayAdapter<Trip>
{
    public TripAdapter(Context context, List<Trip> trips)
    {
        super(context, 0, trips);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Trip trip = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trip_cell, parent, false);
        }
        TextView name = convertView.findViewById(R.id.TripName);
        name.setText(trip.toString());

        return convertView;
    }
}
