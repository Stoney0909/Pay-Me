package com.example.springsophsoft.ui.AddCash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.HomePage;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.AddCash.AddCashFragment;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

public class AddingCardNumber extends AppCompatActivity {
    String cardmoney = "0";
    CardDBHelper db;
    EditText Card_number, cvc, F_name, L_name, month, yearT;
    Button summbit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_card_number);

        Card_number = (EditText)findViewById(R.id.Card_number);
        cvc = (EditText)findViewById(R.id.CVC);
        F_name = (EditText)findViewById(R.id.F_name);
        L_name = (EditText)findViewById(R.id.Last_Name);
        month = (EditText)findViewById(R.id.Eday);
        summbit = (Button)findViewById(R.id.btn_submmit);
        yearT = (EditText)findViewById(R.id.year);

        db = new CardDBHelper(this);
        summbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String card = Card_number.getText().toString();
                String CVCP = cvc.getText().toString();
                String FName = F_name.getText().toString();
                String LName = L_name.getText().toString();
                String EXDAYText =  month.getText().toString();
                String YEARTEXT = yearT.getText().toString();


                if(card.equals("") || CVCP.equals("") || FName.equals("") || LName.equals("") || EXDAYText.equals("") || YEARTEXT.equals(""))
                {
                    toastMessage("Every fields need a input");
                }
                else
                {
                    int EXday = Integer.parseInt( month.getText().toString());
                    int YEAR = Integer.parseInt(yearT.getText().toString());
                    if(card.length() != 16)
                    {
                        toastMessage("Please enter 16 digits for your card");
                        Card_number.setText("");
                    }
                    else if(CVCP.length() != 3)
                    {
                        toastMessage("Please enter the right CVC code");
                        cvc.setText("");
                    }

                    else if(EXday < 1 || EXday > 12)
                    {
                        toastMessage("Please enter month");
                        month.setText("");
                    }
                    else if(YEAR > 3000 || YEAR < 2020)
                    {
                        toastMessage("Please enter the correct year");
                        yearT.setText("");
                    }
                    else
                    {
                        boolean inserting =db.add(card, CVCP, LogIn.getString(), cardmoney, FName, LName, EXday, YEAR);
                        if(inserting)
                        {
                            toastMessage("Adding card success!");
                            AddCash();

                        }
                        else
                        {
                            toastMessage("Adding card fail!");
                        }
                    }

                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void AddCash()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

}
