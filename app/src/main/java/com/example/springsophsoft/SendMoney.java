package com.example.springsophsoft;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.Helper.SendingHelper;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SendMoney extends AppCompatActivity {
    TransactionHelper db;
    Databasehelper databasehelper;
    EditText Amount, Comment;
    Button Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        Amount = (EditText) findViewById(R.id.Amount_Sending);
        Comment = (EditText) findViewById(R.id.Sending_Message);
        Send = (Button) findViewById(R.id.Send);
        db = new TransactionHelper(this);
        databasehelper = new Databasehelper(this);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent i = getIntent();
                String Person_SendingTo = i.getStringExtra("Person_SendingTo");
                String amount = Amount.getText().toString();
                String message = Comment.getText().toString();
                String date = "dd-MMM-yyyy";
                boolean insert = db.add(LogIn.getString(), Person_SendingTo, amount, message, getCurrentDate());
                if (insert) {
                    toastMessage("You successfully sent money");
                    Integer mybal = Integer.parseInt(databasehelper.getBalance()) - Integer.parseInt(amount);
                    databasehelper.updateBalance(mybal.toString(), LogIn.getString());
                    Integer rmoney = Integer.parseInt(databasehelper.getBalanceByUsername(Person_SendingTo)) + Integer.parseInt(amount);
                    databasehelper.updateBalance(rmoney.toString(), Person_SendingTo);
                    Homepage();
                } else toastMessage("Something went wrong");
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void Homepage() {
        Intent intent = new Intent(getApplication(), HomePage.class);
        startActivity(intent);
    }

    public static final String DATE_FORMAT_2 = "dd-MMM-yyyy";

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_2);
        dateFormat.setTimeZone(TimeZone.getDefault());
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }
}
