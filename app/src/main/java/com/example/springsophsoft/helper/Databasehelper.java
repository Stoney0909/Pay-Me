package com.example.springsophsoft.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.springsophsoft.model.Pay_Me;

import javax.xml.datatype.DatatypeConstants;

import static android.content.ContentValues.TAG;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Pay_Me.Contact.TABLE_NAME + " (" +
                    Pay_Me.Contact._ID + " INTEGER PRIMARY KEY," +
                    Pay_Me.Contact.First_Name + " TEXT," +
                    Pay_Me.Contact.Last_Name + " TEXT," +
                    Pay_Me.Contact.Username + "TEXT," +
                    Pay_Me.Contact.Password + "TEXT," +
                    Pay_Me.Contact.Email + "TEXT)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Pay_Me.Contact.TABLE_NAME;



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
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean addData(String firstName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Pay_Me.Contact.First_Name, firstName);

        //Log.d(TAG,"addData: Adding " + firstName + " to " + SQL_CREATE_ENTRIES);

        long result = db.insert(Pay_Me.Contact.TABLE_NAME, null, contentValues);

        //if data is entered correctly then it will return -1
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * from " + Pay_Me.Contact.TABLE_NAME, null);
        return data;
    }








//    public void Query()
//    {
//        SQLiteDatabase db = Databasehelper.getReadableDatabase();
//
//// Define a projection that specifies which columns from the database
//// you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                FeedEntry.COLUMN_NAME_TITLE,
//                FeedEntry.COLUMN_NAME_SUBTITLE
//        };
//
//// Filter results WHERE "title" = 'My Title'
//        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };
//
//// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";
//
//        Cursor cursor = db.query(
//                FeedEntry.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );
//    }

}
