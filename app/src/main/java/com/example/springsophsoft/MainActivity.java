package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.springsophsoft.helper.Databasehelper;


public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    Databasehelper mDatabasehelper;
    private Button btnAdd, btnViewData, btnDeleteDB;
    private EditText editText;

@Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    editText = (EditText) findViewById(R.id.editText);
    btnAdd = (Button) findViewById(R.id.btnAdd);
    btnViewData = (Button) findViewById(R.id.btnViewData);
    mDatabasehelper = new Databasehelper(this);
    btnDeleteDB = (Button) findViewById(R.id.btnDeleteTable);


    btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String newEntry = editText.getText().toString();
            if (editText.length() != 0){
                AddData(newEntry);
                editText.setText("");
            }
            else{
                toastMessage("You must put something in the text field!");
            }
        }
    }

    );
    btnViewData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(intent);
        }
    });

    btnDeleteDB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDatabasehelper.ClearTable();
            toastMessage("Database Successfully deleted.");
        }
    });
}





    public void AddData(String firstName){
        boolean insertData = mDatabasehelper.addData(firstName);
        if (insertData)
        {
            toastMessage("Data Successfully Inserted");
        }
        else
        {
            toastMessage("Something went Wrong");
        }
    }

private void toastMessage(String message){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
}

}



