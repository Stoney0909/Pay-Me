package com.example.springsophsoft.Helper;

import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
public class Databasehelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String TABLE_NAME = "Account_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Username";
    private static final String COL3 = "Email";
    private static final String COL4 = "Passowrd";
    private static final String COL5 = "FirstName";
    private static final String COL6 = "LastName";
    private static final String COL7 = "PhoneNumber";

    //  private DatabaseHelper dbHelper;

    public Databasehelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT," + COL6 + "TEXT,"
                + COL7 + "TEXT)";
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

    public Cursor getItemID(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + username + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteName(int id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + username + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + username + " from database.");
        db.execSQL(query);
    }

    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL3 + "=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public boolean chkusername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL2 + "=?", new String[]{username});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public boolean chkusernamepassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL2 + "=? and " + COL4 + "=?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean AdditionalInfo(String firstname, String lastname, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5, firstname);
        contentValues.put(COL6, lastname);
        contentValues.put(COL7, phone);

        Log.d(TAG, "addData: Adding " + firstname + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + lastname + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + phone + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void Update(String id, String username, String email,String FirstName,String LastName,String Phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET "
                + COL2 + " = '" + username + "'" + "," +
                COL3 + " = '" + email + "'"+"," +
                COL5 + " = '" + FirstName + "'"+ "," +
                COL6 + " = '" + LastName + "'"+
                " Where " + COL1 + " = '" + id + "'" +" AND "+ COL5+ " is NULL AND "+COL6 +" is NULL";

        String quer = " UPDATE " + TABLE_NAME + " SET "+
                COL6 + " = '" + LastName + "'"+
                " Where " + COL1 + " = '" + id + "'" +" AND "+ COL6+ " is Null";

        db.execSQL(query);
        //   db.execSQL(quer);
    }


    public String getUsername() {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[]{COL2},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return username;
    }
    public Cursor fetchAllData() {
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor row = db.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
    public Cursor fetchdatabyfilter(String inputText,String filtercolumn) throws SQLException {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor row = null;
        String query = "SELECT * FROM "+TABLE_NAME;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = db.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+TABLE_NAME+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = db.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
    public Cursor viewData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);

        return cursor;
    }
}