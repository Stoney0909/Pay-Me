package com.example.springsophsoft.ui.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.HomePage;
import com.example.springsophsoft.R;
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

                double balanceAmount =  Double.parseDouble(databasehelper.getBalance());
                double amountInput = Double.parseDouble(amount);

                String formattedValue = String.format("%.2f", balanceAmount);
                String formattedValue2 = String.format("%.2f", amountInput);




                boolean insert = db.add(LogIn.getString(), Person_SendingTo, formattedValue2, message, getCurrentDate());
                if (insert) {
                    double balance = Double.parseDouble(formattedValue);
                    double inputamount = Double.parseDouble(formattedValue2);

                    toastMessage("You successfully sent money");
                    double mybal = balance - inputamount;
                    double rmoney = balance + inputamount;

                    String Value = String.format("%.2f", mybal);
                    String Value2 = String.format("%.2f", rmoney);

                    databasehelper.updateBalance(Value, LogIn.getString());
                    databasehelper.updateBalance(Value2, Person_SendingTo);
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
