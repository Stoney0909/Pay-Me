package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

public class CardDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "CardDBHelper";

    public static final String TABLE_NAME = "Card_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Card_Number";
    private static final String COL3 = "cvc";
    private static final String COL4 = "User_name";
    private static final String COL5 = "Card_balance";
    private static final String COL6 = "FName";
    private static final String COL7 = "LNmae";
    private static final String COL8 = "month";
    private static final String COL9 = "year";

    Databasehelper helper;


    public CardDBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5
                + " TEXT, " + COL6 + " TEXT, " + COL7 + " TEXT, " + COL8 + " TEXT, " + COL9 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean add(String Card, String cvc,String user_name, int cardbalnce, String fname, String lanme, int month, int year)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Card);
        contentValues.put(COL3, cvc);
        contentValues.put(COL4, user_name);
        contentValues.put(COL5, cardbalnce);
        contentValues.put(COL6, fname);
        contentValues.put(COL7, lanme);
        contentValues.put(COL8, month);
        contentValues.put(COL9, year);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    String username = LogIn.getString();
    public Cursor getCardList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " +
                COL4 + " = '" + username + "'", null);
        return query;
    }

    public String getCardBalance(long id) {
        String blance = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereclause = "ID=?";
        String[] where = new String[]{String.valueOf(id)};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,where,null,null,null);
        if(csr.moveToFirst())
        {
            blance = csr.getString(csr.getColumnIndex(COL5));
        }
        return blance;
    }

    public void UpdateCardBalance(String CardNumber, String Amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET "
                + COL5 + " = '" + Amount + "'" +
                " Where " + COL2 + " = '" + CardNumber + "'";
        db.execSQL(query);
    }
}
