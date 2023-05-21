package com.example.packit;

public class Trip {
    private String name;

    public Trip(String name)
    {
        this.name = name;
    }

    public String toString ()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
