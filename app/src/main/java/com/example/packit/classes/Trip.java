package com.example.packit.classes;

import java.util.ArrayList;

public class Trip {
    public static ArrayList<Trip> tripsArrayList = new ArrayList<>();
    public ArrayList<Item> TripItemsArrayList;
    public ArrayList<Tag> TripTagsArrayList;
    public static String TRIP_EDIT_EXTRA = "tripEdit";
    private int ID;
    private String name;

    public Trip(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
        TripTagsArrayList = new ArrayList<>();
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
    public Item getItemForID(int passedItemID)
    {
        for (Item item : TripItemsArrayList)
        {
            if(item.getID() == passedItemID)
                return item;
        }
        return null;
    }
    public Tag getTagForID(int passedTagID)
    {
        for (Tag tag : TripTagsArrayList)
        {
            if(tag.getID() == passedTagID)
                return tag;
        }
        return null;
    }
}
