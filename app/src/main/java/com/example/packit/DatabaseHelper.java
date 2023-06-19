package com.example.packit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbHelper;
    private static final String DATABASE_NAME = "ToPackDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "trips";
    private static final String TRIPS_ID = "_id";
    private static final String TRIPS_NAME = "name";
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
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
        + TRIPS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + TRIPS_NAME + " TEXT" + ")";
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

        db.insert(TABLE_NAME, null, values);
    }
    public void updateTripInDB(Trip selectedTrip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRIPS_ID, selectedTrip.getID()); //puts the same ID in the same place
        values.put(TRIPS_NAME, selectedTrip.toString());

        db.update(TABLE_NAME, values, TRIPS_ID + " =? ", new String[]{String.valueOf(selectedTrip.getID())});
    }
    public void deleteTrip(Trip selectedTrip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, TRIPS_ID + "=?", new String[]{String.valueOf(selectedTrip.getID())});
        db.close();
    }
    public Cursor getTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
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
                Trip trip = new Trip(id,name);
                Trip.tripsArrayList.add(trip);
            }
        }
    }
}