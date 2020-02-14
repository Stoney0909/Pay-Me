package com.example.springsophsoft;

import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.Helper.Databasehelper;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";
    private ListView mListView;
    Databasehelper mDatabaseHelper;
    private Button add;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new Databasehelper(this);
        populateListView();



    }
    private void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        //get the data and append to a List
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
