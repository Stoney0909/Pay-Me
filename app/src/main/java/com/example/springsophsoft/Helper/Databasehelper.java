package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class Databasehelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "Account_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Username";
    private static final String COL3 = "Email";
    private static final String COL4 = "Passowrd";
    private static final String COL5 = "FirstName";
    private static final String COL6 = "LastName";

    public Databasehelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 +" TEXT," + COL6 + "TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, email);
        contentValues.put(COL4, password);

        Log.d(TAG, "addData: Adding " + username + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + email + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + password + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getItemID(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + username + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteName(int id, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + username + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + username + " from database.");
        db.execSQL(query);
    }

    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL3 + "=?", new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public boolean chkusername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL2 + "=?", new String[]{username});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public boolean chkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL2 + "=? and " + COL4 + "=?", new String[]{username, password});
        if (cursor.getCount()>0)return true;
        else return false;
    }
}

