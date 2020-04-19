package com.example.springsophsoft.ui.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.R;

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
        String s = "Email: " + mydb.getSearchPersonEmail(Person_SendingTo);
        email.setText(s);
        phone = (TextView) findViewById(R.id.phone);
        String o = "Phone Number: " + mydb.getSearchpersonPhone(Person_SendingTo);

        if(mydb.getSearchpersonPhone(Person_SendingTo) == null && mydb.getSearchpersonPhone(Person_SendingTo) == null)
        {
            phone.setText("Phone Number: None");
        }
        else if(mydb.getSearchpersonPhone(Person_SendingTo).equals("null") && mydb.getSearchpersonPhone(Person_SendingTo).equals("null"))
        {
            phone.setText("Phone Number: None");
        }
        else if(mydb.getSearchpersonPhone(Person_SendingTo) != null && mydb.getSearchpersonPhone(Person_SendingTo) != null)
        {
            phone.setText(o);
        }

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
                Intent intent = new Intent(Send_Request.this, com.example.springsophsoft.ui.Search.Request.class);
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