package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    com.example.springsophsoft.Helper.Databasehelper Databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void AddData(String newEntry){
        boolean insertData = Databasehelper.addData(newEntry);

        if (insertData){
            toastMessage("Data Sucessfully Inserted");
        }
        else{
            toastMessage("Something Went Wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
