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
                + TAG_DESCRIPTION + " TEXT" + ")";
        db.execSQL(createTableQuery);

        createTableQuery = "CREATE TABLE " + TABLE_JUNCTION + "("
                + JUNCTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + JUNCTION_TAG_ID + " INTEGER,"
                + JUNCTION_ITEM_ID + " INTEGER" + ")";
        db.execSQL(createTableQuery);

        createTableQuery = "CREATE TABLE " + TABLE_ITEMS + "("
                + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ITEM_NAME + " TEXT,"
                + ITEM_DESCRIPTION + " TEXT" + ")";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

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

        db.insert(TABLE_ITEMS, null, values);
    }
    public void updateTripInDB(Trip selectedTrip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRIPS_ID, selectedTrip.getID()); //puts the same ID in the same place
        values.put(TRIPS_NAME, selectedTrip.toString());

        db.update(TABLE_TRIPS, values, TRIPS_ID + " =? ", new String[]{String.valueOf(selectedTrip.getID())});
    }
    public void deleteTrip(Trip selectedTrip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIPS, TRIPS_ID + "=?", new String[]{String.valueOf(selectedTrip.getID())});
        db.close();
    }
    public Cursor getTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRIPS;
        return db.rawQuery(query, null);
    }
    public void populateTripsListArray()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = getTrips();

        if(result.getCount() != 0)
        {
            while(result.moveToNext())
            {
                int id = result.getInt(0);
                String name = result.getString(1);
                Log.d("TAG", "name: " + name);
                Trip trip = new Trip(id,name);
                Trip.tripsArrayList.add(trip);
            }
        }
    }
}