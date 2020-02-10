package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.springsophsoft.model.Database_Module;

import static android.content.ContentValues.TAG;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Database_Module.Contact.TABLE_NAME + " (" +
                    Database_Module.Contact._ID + " INTEGER PRIMARY KEY," +
                    Database_Module.Contact.First_Name + " STRING," +
                    Database_Module.Contact.Last_Name + " STRING," +
                    Database_Module.Contact.Username + "STRING," +
                    Database_Module.Contact.Password + "STRING," +
                    Database_Module.Contact.Email + "STRING)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Database_Module.Contact.TABLE_NAME;



    private static final String DATABASE_NAME = "Pay_Me.db";




    public Databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database_Module.Contact.TABLE_NAME);
        onCreate(db);
    }
//    public boolean addData(String item)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(First_Name, item);
//
//        Log.d(TAG,"addData: Adding " + item + " to " + TABLE_NAME);
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        if (result == -1){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
}
