package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.ui.AddCash.AddCashFragment;

public class TransMoney_card extends AppCompatActivity {

    CardDBHelper Carddb;
    Databasehelper mydb;
    private EditText Tran;
    private Button add, drop;

    String AccountAmount;
    int CardAmount = AddCashFragment.getCardBalance();
    int COnvAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_money_card);

        Carddb = new CardDBHelper(this);
        mydb = new Databasehelper(this);

        Tran = (EditText)findViewById(R.id.Money);
        add = (Button)findViewById(R.id.btn_add_to_card);
        drop = (Button)findViewById(R.id.btn_drop_to_card);

        AccountAmount = mydb.getBalance();
        COnvAmount = Integer.parseInt(AccountAmount);

        toastMessage("Your Account balance: " + COnvAmount + "\nYour card balance: " + CardAmount);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adding();
            }
        });


        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                droping();
            }
        });


    }



    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public void adding()
    {
        String input =  Tran.getText().toString();
        int money =  Integer.parseInt(input); // Input
        String Cardnumber = AddCashFragment.getCard();
            if(COnvAmount < money)
            {
                toastMessage("You don't have enough money to make a transfer, account Balance: " + COnvAmount);
                Tran.setText("");
            }
            else
            {
                int calCardAmount = CardAmount + money;
                String Balance = Integer.toString(calCardAmount);
                Carddb.UpdateCardBalance(Cardnumber, Balance);

                int CalAccAmount = COnvAmount - money;
                String ACCBalance = Integer.toString(CalAccAmount);
                mydb.updateBalance(ACCBalance);

                toastMessage("Transfer success,Your card balance: " + Balance);
            }

    }

    public void droping()
    {
        String Cardnumber = AddCashFragment.getCard();
        int money =  Integer.parseInt( Tran.getText().toString());
        if(CardAmount < money)
        {
            toastMessage("You don't have enough money to make a transfer, card Balance: " + CardAmount);
            Tran.setText("");
        }
        else {
            int calAmount = CardAmount - money;
            String Balance = Integer.toString(calAmount);
            Carddb.UpdateCardBalance(Cardnumber, Balance);

            int CalAccAmount = COnvAmount + money;
            String ACCBalance = Integer.toString(CalAccAmount);
            mydb.updateBalance(ACCBalance);

            toastMessage("CardBalance = " + Balance);
        }

    }

}
