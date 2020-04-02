package com.example.springsophsoft.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;


public class TransactionHelper  extends SQLiteOpenHelper {

    private static final String TAG = "TransactionHelper";

    private static final String TABLE_NAME = "Transaction_Table";
    private static final String TABLE_NAME2 = "Request_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "amount";
    private static final String COL3 = "receiver";
    private static final String COL4 = "sender";
    private static final String COL5 = "message";
    private static final String COL6 = "date";
    private static final String COL7 = "status";





    public TransactionHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" INTEGER, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 +" TEXT, " + COL6 + " TEXT, "
                + COL7 + " TEXT)";
        db.execSQL(createTable);


        String createTable2 = "CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" INTEGER, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 +" TEXT, " + COL6 + " TEXT, "
                + COL7 + " TEXT)";
        db.execSQL(createTable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
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
    public Cursor getAllData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COL4 + "=? or " + COL3 + "=?", new String[]{id, id});
        return cursor;
    }
    public boolean add(String Person_sending, String Person_receiving,String amount,String Message, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL4, Person_sending);
        contentValues.put(COL3, Person_receiving);
        contentValues.put(COL2, amount);
        contentValues.put(COL5, Message);
        contentValues.put(COL6, date);
        contentValues.put(COL7,"SENT");
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
    public boolean Request(String Person_sending, String Person_requesting,String amount,String Message, String date)
        {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL4, Person_sending);
        contentValues.put(COL3, Person_requesting);
        contentValues.put(COL2, amount);
        contentValues.put(COL5, Message);
        contentValues.put(COL6, date);
        contentValues.put(COL7,"Request");
        Log.d(TAG, "addData: Adding " + Person_sending + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + Person_requesting + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + amount + " to " + TABLE_NAME);
            Log.d(TAG, "addData: Adding " + Message + " to " + TABLE_NAME);
            Log.d(TAG, "addData: Adding " + date + " to " + TABLE_NAME);
            Log.d(TAG, "addData: Adding " + "Request" + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1) {
            return false;
        }
        else
        {
            return true;
        }
    }
    public String getInfo(int number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor row = db.rawQuery("Select * from " + TABLE_NAME2 + " where " + COL4 + "=?", new String[]{LogIn.getString()});
        if (row.moveToFirst()) {
            if (number == 1) {
                return  row.getString(row.getColumnIndex(COL2));
            }
            if(number==2)
            {
                return row.getString(row.getColumnIndex(COL3));
            }
            if(number==3) {
                return row.getString(row.getColumnIndex(COL6));
            }
        }
        return "";
    }
    public int numberOfNotification() {
        int count=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME2 + " where " + COL4 + "=?", new String[]{LogIn.getString()});
        count = cursor.getCount();
        cursor.close();
        return count;
    }
    public Cursor getData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME2 + " where " + COL4 + "=?", new String[]{LogIn.getString()});
        return cursor;
    }
   public void Delete(String name, String amount){
       SQLiteDatabase db = this.getReadableDatabase();
       String query= "Delete from "+TABLE_NAME2+" Where "+COL3+ " = '" + name + "'"+" And "+COL2+
               " = '" + amount + "'";
       db.execSQL(query);
   }
}