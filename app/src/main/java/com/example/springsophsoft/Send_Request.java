package com.example.springsophsoft;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.Helper.Databasehelper;

public class Send_Request extends AppCompatActivity {
    private TextView phone, email, username;
    private Button Send, Request;


    Databasehelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_send);
        mydb = new Databasehelper(this);
        Intent i = getIntent();
        final String Person_SendingTo = i.getStringExtra("Person_SendingTo");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));



        Send = (Button) findViewById(R.id.sending);
        Request = (Button) findViewById(R.id.requesting);
        username = (TextView) findViewById(R.id.use);
        String m = "UserName: " + Person_SendingTo;
        username.setText(m);
        email = (TextView) findViewById(R.id.email);
        String s = "Email: " + mydb.getInfo(5);
        email.setText(s);
        phone = (TextView) findViewById(R.id.phone);
        String o = "Phone Number: " + mydb.getInfo(3);
        phone.setText(o);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Send_Request.this, SendMoney.class);
                String name= Person_SendingTo;
                intent.putExtra("Person_SendingTo",name);
                startActivity(intent);
                finish();

            }
        });
        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Send_Request.this, Send_Request.class);
                String name=Person_SendingTo;
                intent.putExtra("Person_SendingTo",name);
                startActivity(intent);

            }
        });
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}