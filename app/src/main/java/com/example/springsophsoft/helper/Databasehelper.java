package com.example.springsophsoft.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.springsophsoft.model.Pay_Me;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String Create_Contacts =
            "CREATE TABLE " + Pay_Me.Contact.TABLE_NAME + " (" +
                    Pay_Me.Contact._ID + " TEXT PRIMARY KEY," +
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
        db.execSQL(Create_Contacts);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    //Add a new row of data to the database
    public boolean addData(String firstName)
    {
//        String lastName, String Username, String Password, String Email
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Pay_Me.Contact.First_Name, firstName);
//        contentValues.put(Pay_Me.Contact.Last_Name, lastName);
//        contentValues.put(Pay_Me.Contact.Username, Username);
//        contentValues.put(Pay_Me.Contact.Password, Password);
//        contentValues.put(Pay_Me.Contact.Email, Email);

        //Log.d(TAG,"addData: Adding " + firstName + " to " + Create_Contacts);

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
    public void ClearTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Pay_Me.Contact.TABLE_NAME,null, null);

    }


//    public void updateUser() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//
//        // updating row
//        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }

    public boolean checkUser(String username, String password) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query((Pay_Me.Contact.TABLE_NAME),
                new String[]{Pay_Me.Contact._ID, Pay_Me.Contact.Username, Pay_Me.Contact.Password},//Selecting columns want to query
                Pay_Me.Contact.Username + "=?",
                new String[]{username},//Where clause
                null, null, null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    }

