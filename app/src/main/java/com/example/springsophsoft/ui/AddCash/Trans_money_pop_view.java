package com.example.springsophsoft.ui.AddCash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.R;


public class Trans_money_pop_view extends AppCompatActivity {

    private TextView message;
    private Button Y, N;
    String s;
    CardDBHelper Carddb;
    Databasehelper mydb;
    public static String returnV = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trans_money_pop_view);

        Carddb = new CardDBHelper(this);
        mydb = new Databasehelper(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));


        message = (TextView) findViewById(R.id.mess);
        String m = "You want to add $" + TransMoney_card.amountEnter() + " into your card?";
        message.setText(m);

        Y = (Button) findViewById(R.id.yes);
        N = (Button) findViewById(R.id.no);

        Y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cardNumber = TransMoney_card.cardNUmber();
                String CardBalance = TransMoney_card.cardAmount();
                String AccBalance = TransMoney_card.ACCbalance();

                Carddb.UpdateCardBalance(cardNumber, CardBalance);
                mydb.updateBalance(AccBalance);
                toastMessage("Transfer success. PLease go back to home and see the new update");
                finish();
            }
        });
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("You canceled the transfer.");
                finish();
            }
        });


    }
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
