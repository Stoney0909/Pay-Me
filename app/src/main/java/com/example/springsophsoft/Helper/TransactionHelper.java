package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class TransactionHelper  extends SQLiteOpenHelper {

    private static final String TAG = "TransactionHelper";

    private static final String TABLE_NAME = "Transaction_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "amount";
    private static final String COL3 = "receiver";
    private static final String COL4 = "sender";
    private static final String COL5 = "message";
    private static final String COL6 = "date";




    public TransactionHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" INTEGER, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 +" TEXT," + COL6 + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean addData(Integer amount, String reciever, String sender, String message, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, amount);
        contentValues.put(COL3, reciever);
        contentValues.put(COL4, sender);
        contentValues.put(COL5, message);
        contentValues.put(COL6, date);

        Log.d(TAG, "addData: Adding " + amount + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + reciever + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + sender + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + message + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + date + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataRecieved(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL3 + "=?", new String[]{id});
        return cursor;
    }
    public Cursor getDataSent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL4 + "=?", new String[]{id});
        return cursor;
    }

    public boolean add(String Person_sending, String Person_receiving,String amount,String Message)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL4, Person_sending);
        contentValues.put(COL3, Person_receiving);
        contentValues.put(COL2, amount);
        contentValues.put(COL5, Message);
        Log.d(TAG, "addData: Adding " + Person_sending + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + Person_receiving + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + amount + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else
        {
            return true;
        }
    }


}