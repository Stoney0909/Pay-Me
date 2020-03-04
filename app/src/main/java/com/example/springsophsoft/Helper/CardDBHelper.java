package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CardDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "CardDBHelper";

    private static final String TABLE_NAME = "Card_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Card_Number";
    private static final String COL3 = "cvc";
    private static final String COL4 = "First_Name";
    private static final String COL5 = "Last_Name";
    private static final String COL6 = "Eday";
    private static final String COL7 = "Eyear";
    private static final String COL8 = "Person_ID";

    Databasehelper helper;



    public CardDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 +" TEXT," + COL6 + "TEXT,"
                + COL7 + "TEXT," + COL8 + "TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public boolean addCard(String Card, String cvc, String Fname, String Lname, String day, String year)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Card);
        contentValues.put(COL3, cvc);
        contentValues.put(COL4, Fname);
        contentValues.put(COL5, Lname);
        contentValues.put(COL6, day);
        contentValues.put(COL7, year);

        Log.d(TAG, "addData: Adding " + Card + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + cvc + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + Fname + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + Lname + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + day + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + year + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else
            {
            return true;
        }
    }

    public Cursor getCardList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return query;
    }

    public void setPersonNumber()
    {

    }
}
