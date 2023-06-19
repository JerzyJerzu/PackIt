package com.example.packit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbHelper;
    private static final String DATABASE_NAME = "ToPackDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TRIPS = "trips";
    private static final String TRIPS_ID = "_id";
    private static final String TRIPS_NAME = "name";

    private static final String TABLE_TAGS = "tags";
    private static final String TAG_ID = "_id";
    private static final String TAG_TRIP_ID = "trip_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";

    private static final String TABLE_JUNCTION = "junctions";
    private static final String JUNCTION_ID = "_id";
    private static final String JUNCTION_TAG_ID = "tag_id";
    private static final String JUNCTION_ITEM_ID = "item_id";

    private static final String TABLE_ITEMS = "tags";
    private static final String ITEM_ID = "_id";
    private static final String ITEM_NAME = "name";
    private static final String ITEM_DESCRIPTION = "description";
    private static final String ITEM_CHECKED = "checked";
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DatabaseHelper instanceOfDatabase(Context context)
    {
        if(dbHelper == null)
        {
            dbHelper = new DatabaseHelper(context);
        }
        return dbHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        initialiseTables(db);
    }
    private void initialiseTables(SQLiteDatabase db)
    {
        String createTableQuery = "CREATE TABLE " + TABLE_TRIPS + "("
                + TRIPS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TRIPS_NAME + " TEXT" + ")";
        db.execSQL(createTableQuery);

        createTableQuery = "CREATE TABLE " + TABLE_TAGS + "("
                + TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TAG_TRIP_ID + " INTEGER,"
                + TAG_NAME + " TEXT,"
                + TAG_DESCRIPTION + " TEXT,"
                + "FOREIGN KEY (" + TAG_TRIP_ID + ") REFERENCES "
                + TABLE_TRIPS + "(" + TRIPS_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createTableQuery);

        createTableQuery = "CREATE TABLE " + TABLE_ITEMS + "("
                + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ITEM_NAME + " TEXT,"
                + ITEM_DESCRIPTION + " TEXT,"
                + ITEM_CHECKED + " BOOLEAN"
                + ")";
        db.execSQL(createTableQuery);

        createTableQuery = "CREATE TABLE " + TABLE_JUNCTION + "("
                + JUNCTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + JUNCTION_TAG_ID + " INTEGER,"
                + JUNCTION_ITEM_ID + " INTEGER,"
                + "FOREIGN KEY (" + JUNCTION_TAG_ID + ") REFERENCES "
                + TABLE_TAGS + "(" + TAG_ID + ") ON DELETE CASCADE,"
                + "FOREIGN KEY (" + JUNCTION_ITEM_ID + ") REFERENCES "
                + TABLE_ITEMS + "(" + ITEM_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }
    public void addtrip(Trip trip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRIPS_ID, trip.getID());
        values.put(TRIPS_NAME, trip.toString());

        db.insert(TABLE_TRIPS, null, values);
    }
    public void addNewTag(Tag tag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAG_ID, tag.getID());
        values.put(TAG_TRIP_ID, tag.getTripID());
        values.put(TAG_NAME, tag.getName());
        values.put(TAG_DESCRIPTION, tag.getDescription());

        db.insert(TABLE_TAGS, null, values);
    }
    public void addJunction(Tag tag, Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JUNCTION_TAG_ID, tag.getID());
        values.put(JUNCTION_ITEM_ID, item.getID());

        db.insert(TABLE_JUNCTION, null, values);
    }
    public void addNewItem(Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_ID, item.getID());
        values.put(ITEM_NAME, item.getName());
        values.put(ITEM_DESCRIPTION, item.getDescription());
        values.put(ITEM_CHECKED, item.getChecked());

        db.insert(TABLE_ITEMS, null, values);
    }
    public void updateTripInDB(Trip selectedTrip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(TRIPS_ID, selectedTrip.getID()); //puts the same ID in the same place
        values.put(TRIPS_NAME, selectedTrip.toString());

        db.update(TABLE_TRIPS, values, TRIPS_ID + " =? ", new String[]{String.valueOf(selectedTrip.getID())});
    }
    public void UpdateTagInDB(Tag tag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAG_NAME, tag.getName());
        values.put(TAG_DESCRIPTION, tag.getDescription());

        db.update(TABLE_TAGS, values, TAG_ID + " =? ", new String[]{String.valueOf(tag.getID())});
    }
    public void UpdateItemInDB(Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getName());
        values.put(ITEM_DESCRIPTION, item.getDescription());
        values.put(ITEM_CHECKED, item.getChecked());

        db.update(TABLE_ITEMS, values, ITEM_ID + " =? ", new String[]{String.valueOf(item.getID())});
    }
    public void deleteTrip(Trip selectedTrip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIPS, TRIPS_ID + "=?", new String[]{String.valueOf(selectedTrip.getID())});
        db.close();
    }
    public void deleteTag(Tag tag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TAGS, TAG_ID + "=?", new String[]{String.valueOf(tag.getID())});
        db.close();
    }
    public void deleteAllJunctionsWithItem(int ItemID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUNCTION,
                JUNCTION_ITEM_ID + "=?",
                new String[]{String.valueOf(ItemID)});
        db.close();
    }
    public void deleteAllJunctionsWithTag(int TagID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUNCTION,
                JUNCTION_TAG_ID + "=?",
                new String[]{String.valueOf(TagID)});
        db.close();
    }
    public void deleteJunction(int ItemID, int TagID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUNCTION,
                JUNCTION_ITEM_ID + "=? AND " + JUNCTION_TAG_ID + "=?",
                new String[]{String.valueOf(ItemID),String.valueOf(TagID)});
        db.close();
    }
    public void deleteItem(Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, ITEM_ID + "=?", new String[]{String.valueOf(item.getID())});
        db.close();
    }
    public void populateTripsListArray()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_TRIPS, null);;

        if(result.getCount() != 0)
        {
            while(result.moveToNext())
            {
                int id = result.getInt(0);
                String name = result.getString(1);
                Trip trip = new Trip(id,name);
                Trip.tripsArrayList.add(trip);
            }
        }
    }
}