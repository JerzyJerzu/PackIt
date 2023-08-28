package com.example.packit.classes;

import java.util.ArrayList;

public class Item {
    public static String ITEM_EDIT_EXTRA = "ItemEdit";
    public ArrayList<Tag> ItemTagsArrayList;
    private int ID;
    private int TripID;
    private String name;
    private String description;
    private Boolean checked;
    public Item(int ID,int TripID, String name, String description,boolean checked)
    {
        this.TripID = TripID;
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.ItemTagsArrayList = new ArrayList<>();
        this.checked = checked;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public int getTripID() {
        return TripID;
    }
}
