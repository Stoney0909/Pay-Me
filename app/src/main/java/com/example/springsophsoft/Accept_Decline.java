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
import com.example.springsophsoft.Helper.TransactionHelper;

public class Accept_Decline extends AppCompatActivity {
    private TextView phone, email, username;
    private Button Accept, Decline;


    TransactionHelper mydb;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_decline);
        mydb = new TransactionHelper(this);
        Intent i = getIntent();
        final String name = i.getStringExtra("Demandeur");
        final String amount = i.getStringExtra("Montant");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));



        Accept = (Button) findViewById(R.id.accept);
        Decline = (Button) findViewById(R.id.decline);
        username = (TextView) findViewById(R.id.use);
        String m = "Send $" + amount+" To " + name;
        username.setText(m);

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Accept_Decline.this, SendMoney.class);
                intent.putExtra("Person_SendingTo",name);
                startActivity(intent);
                mydb.Delete(name,amount);
                finish();

            }
        });
        Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toastMessage("You successfully denied");
                mydb.Delete(name,amount);
                finish();

            }
        });
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}