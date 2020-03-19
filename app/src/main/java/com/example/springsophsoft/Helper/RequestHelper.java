package com.example.springsophsoft.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RequestHelper extends SQLiteOpenHelper {
    private static final String TAG = "RequestHelper";

    private static final String TABLE_NAME = "Request_Table";
    private static final String COL1 = "ID";
    private static final String COL2 = "amount";
    private static final String COL3 = "receiver";
    private static final String COL4 = "sender";
    private static final String COL5 = "message";
    private static final String COL6 = "date";
    private static final String COL7 = "status";


    public RequestHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" INTEGER, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 +" TEXT, " + COL6 + " TEXT, "
                + COL7 + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
