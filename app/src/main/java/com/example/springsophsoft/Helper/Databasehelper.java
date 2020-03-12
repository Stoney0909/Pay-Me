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

import com.example.springsophsoft.LogIn;
import com.example.springsophsoft.SignUp;

import java.util.ArrayList;
import java.util.List;
public class Databasehelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String TABLE_NAME = "Account_Table";
    public static final String COL1 = "ID";
    public static final String COL2 = "Username";
    public static final String COL3 = "Email";
    public static final String COL4 = "Passowrd";
    public static final String COL5 = "FirstName";
    public static final String COL6 = "LastName";
    public static final String COL7 = "PhoneNumber";
    public static final String COL8 = "Balance";

    public Databasehelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT," + COL6 + " TEXT,"
                + COL7 + " TEXT, " + COL8 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String getPassword()
    {
        String usernanme = LogIn.getString();
        String password = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "Username=?";
        String[] where = new String[]{usernanme};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            password = csr.getString(csr.getColumnIndex(COL4));
        }
        return password;
    }

    public void updatePassword(String p)
    {
        String user = LogIn.getString();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET "
                + COL4 + " = '" + p + "'" +
                " Where " + COL2 + " = '" + user + "'";
        db.execSQL(query);
    }

    public String getBalance() {
        String usernanme = LogIn.getString();
        String blance = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "Username=?";
        String[] where = new String[]{usernanme};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            blance = csr.getString(csr.getColumnIndex(COL8));
        }
        return blance;
    }

    public void updateBalance(String balance)
    {
        String user = LogIn.getString();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET "
                + COL8 + " = '" + balance + "'" +
                " Where " + COL2 + " = '" + user + "'";
        db.execSQL(query);
    }

    public boolean addData(String username, String email, String password, int bal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, email);
        contentValues.put(COL4, password);
        contentValues.put(COL8, bal);

        Log.d(TAG, "addData: Adding " + username + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + email + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + password + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

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

    public void Update(String id, String username, String email,String FirstName,String LastName,String Phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET " +
                  COL2 + " = '" + username + "'" + "," +
                  COL3 + " = '" + email + "'" + "," +
                  COL5 + " = '" + FirstName + "'"+ "," +
                  COL6 + " = '" + LastName + "'" + ", " +
                  COL7 + " = '" + Phone + "'" +
                " Where " + COL2 + " = '" + id + "'";
        db.execSQL(query);
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

    public String getFirstName()
    {
        String usernanme = LogIn.getString();
        String Fname = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "Username=?";
        String[] where = new String[]{usernanme};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            Fname = csr.getString(csr.getColumnIndex(COL5));
        }
        return Fname;
    }

    public String getLastName()
    {
        String usernanme = LogIn.getString();
        String Lname = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "Username=?";
        String[] where = new String[]{usernanme};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            Lname = csr.getString(csr.getColumnIndex(COL6));
        }
        return Lname;
    }

    public String getPhone()
    {
        String usernanme = LogIn.getString();
        String Phone = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "Username=?";
        String[] where = new String[]{usernanme};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            Phone = csr.getString(csr.getColumnIndex(COL7));
        }
        return Phone;
    }

    public String getEmail()
    {
        String usernanme = LogIn.getString();
        String Email = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "Username=?";
        String[] where = new String[]{usernanme};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            Email = csr.getString(csr.getColumnIndex(COL3));
        }
        return Email;
    }


}