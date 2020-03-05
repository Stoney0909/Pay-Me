package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SendingHelper extends SQLiteOpenHelper {

    private static final String TAG = "SendBHelper";

    private static final String TABLE_NAME = "Sending_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Person_Sending";
    private static final String COL3 = "Person_Receiving";
    private static final String COL4 = "Amount";
    private static final String COL5 = "Message";

    public SendingHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + "TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean add(String Person_sending, String Person_receiving,String amount,String Message)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Person_sending);
        contentValues.put(COL3, Person_receiving);
        contentValues.put(COL4, amount);
      //  contentValues.put(COL5, Message);
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
