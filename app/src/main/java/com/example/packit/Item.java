package com.example.packit;

import java.util.ArrayList;

public class Item {
    public static String ITEM_EDIT_EXTRA = "ItemEdit";
    public ArrayList<Tag> ItemTagsArrayList;
    private int ID;
    private String name;
    private String description;
    public Item(int ID, String name, String description)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        ItemTagsArrayList = new ArrayList<>();
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
}
