package com.example.springsophsoft;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.ui.AddCash.AddCashFragment;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class TransMoney_card extends AppCompatActivity {

    CardDBHelper Carddb;
    Databasehelper mydb;
    private EditText Tran;
    private Button add, drop;
    public static String amount = "";
    public static String CardAmout = "";
    public static String CardN = "";
    public static String AccB = "";
    private TextView showmoney;

    String AccountAmount;
    double CardAmount = AddCashFragment.getCardBalance();
    double COnvAmount;

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
        COnvAmount = Double.parseDouble(AccountAmount);
        String formattedValue = String.format("%.2f", CardAmount);
        String formattedValue2 = String.format("%.2f", COnvAmount);

        showmoney = (TextView) findViewById(R.id.showAmount);
        String display1 = "Card amount: $" + formattedValue;
        String display2 = "Account amount: $" + formattedValue2;
        showmoney.setText(display1 + "\n\n" + display2);



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
    public void otherclass()
    {
        Intent i = new Intent(this, Trans_money_pop_view.class);
        startActivity(i);
    }


    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public void adding()
    {
        String input =  Tran.getText().toString();
        String Cardnumber = AddCashFragment.getCard();
        if(input.equals(""))
        {
            toastMessage("The field need a input");
        }
        else
        {
            double money = Double.parseDouble(input);
            String formattedValue = String.format("%.2f", money);
            double money2 = Double.parseDouble(formattedValue);
            if(COnvAmount < money2)
            {
                toastMessage("You don't have enough money to make a transfer, account Balance: " + COnvAmount);
                Tran.setText("");
            }
            else
            {
                amount = Double.toString(money2);

                double calCardAmount = CardAmount + money2;
                String Balance = Double.toString(calCardAmount);

                double CalAccAmount = COnvAmount - money2;
                String ACCBalance =  Double.toString(CalAccAmount);

                CardAmout = Balance;
                CardN = Cardnumber;
                AccB = ACCBalance;

                otherclass();
            }
        }
    }

    public void otherclass2()
    {
        Intent i = new Intent(this, Trans_money_pop_view2.class);
        startActivity(i);
    }

    public void droping()
    {
        String input =  Tran.getText().toString();
        String Cardnumber = AddCashFragment.getCard();
        if(input.equals(""))
        {
            toastMessage("The field need a input");
        }
        else
        {
            double money = Double.parseDouble(input);
            String formattedValue = String.format("%.2f", money);
            double money2 = Double.parseDouble(formattedValue);
            if(CardAmount < money)
            {
                toastMessage("You don't have enough money to make a transfer, card Balance: " + CardAmount);
                Tran.setText("");
            }
            else {
                amount = Double.toString(money2);

                double calAmount = CardAmount - money2;
                String Balance = Double.toString(calAmount);

                double CalAccAmount = COnvAmount + money2;
                String ACCBalance = Double.toString(CalAccAmount);

                CardAmout = Balance;
                CardN = Cardnumber;
                AccB = ACCBalance;

                otherclass2();
            }

        }

    }


    public static String cardNUmber()
    {
        String cardN;
        cardN = CardN;
        return cardN;
    }

    public static String cardAmount()
    {
        String money;
        money = CardAmout;
        return money;
    }

    public static String ACCbalance()
    {
        String balance;
        balance = AccB;
        return balance;
    }

    public static String amountEnter()
    {
        String money = amount;
        return money;
    }

}