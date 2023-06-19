package com.example.packit;

import java.util.ArrayList;

public class Trip {
    public static ArrayList<Trip> tripsArrayList = new ArrayList<>();
    public ArrayList<Item> TripItemsArrayList;
    public ArrayList<Tag> TagsArrayList;
    public static String TRIP_EDIT_EXTRA = "tripEdit";
    private int ID;
    private String name;

    public Trip(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
        TagsArrayList = new ArrayList<>();
        TripItemsArrayList = new ArrayList<>();
    }

    public String toString ()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }
    public static Trip getTripForID(int passedNoteID)
    {
        for (Trip trip : tripsArrayList)
        {
            if(trip.getID() == passedNoteID)
                return trip;
        }

        return null;
    }
}
