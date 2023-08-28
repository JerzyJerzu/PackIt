package com.example.packit.classes;

import java.util.ArrayList;

public class Tag {
    public static String TAG_EDIT_EXTRA = "tagEdit";
    public ArrayList<Item> TagItemsArrayList;
    private int ID;
    private int TripID;
    private String name;
    private String description;
    public Tag(int ID, int TripID, String name, String description)
    {
        this.ID = ID;
        this.name = name;
        this.TripID = TripID;
        this.description = description;
        TagItemsArrayList = new ArrayList<>();
    }
    public int getID() {
        return ID;
    }
    public int getTripID() {
        return TripID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String decsription) {
        this.description = decsription;
    }
}
