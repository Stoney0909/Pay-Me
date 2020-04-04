package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.springsophsoft.Helper.CardDBHelper;
import com.example.springsophsoft.ui.AddCash.AddCashFragment;

public class Addingcard_popView extends AppCompatActivity {

    private Button Tmoney, Delete;
    private TextView amount;
    CardDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingcard_pop_view);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        db = new CardDBHelper(this);
        amount = (TextView)findViewById(R.id.AmountCard);
        Tmoney = (Button)findViewById(R.id.WithD);
        Delete = (Button)findViewById(R.id.DeleteCardN);

        String money = Double.toString(AddCashFragment.getCardBalance());
        amount.setText("Card amount: $" + money);


        Tmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                otherpage();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cardnumber = AddCashFragment.getCard();
                db.deleteCardInfo(Cardnumber);
                finish();
                toastMessage("Delete success. Please refresh this page");
            }
        });
    }

    public void otherpage()
    {
        Intent intent = new Intent(this, TransMoney_card.class);
        startActivity(intent);
    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
