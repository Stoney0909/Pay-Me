package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.ui.AddCash.AddCashFragment;

public class AddingCardNumber extends AppCompatActivity {

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

        db=new CardDBHelper(this);
        summbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String card = Card_number.getText().toString();
                String CVCP = cvc.getText().toString();
                String FName = F_name.getText().toString();
                String LName = L_name.getText().toString();
                String EXday = month.getText().toString();
                String YEAR = yearT.getText().toString();

                if(card.length() != 16)
                {
                    toastMessage("Please enter 16 digits for your card");
                }
                if(CVCP.length() != 3)
                {
                    toastMessage("Please enter the right CVC code");
                }

                if(EXday.length() != 2)
                {
                    toastMessage("Please enter month");
                }
                if(YEAR.length() != 4)
                {
                    toastMessage("Please enter year");
                }
                if(card.length() == 16 && CVCP.length() == 3 && EXday.length() == 2 && YEAR.length() == 4)
                {
                    boolean insertData = db.addCard(card, CVCP, FName, LName, EXday, YEAR);
                    if(insertData)
                    {
                        toastMessage("Adding card success!");
                        Intent intent = new Intent(getApplication(), AddCashFragment.class);
                        startActivity(intent);
                    }
                    else
                    {
                        toastMessage("Adding card fail!");
                    }
                }

                }


        });
    }


    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
