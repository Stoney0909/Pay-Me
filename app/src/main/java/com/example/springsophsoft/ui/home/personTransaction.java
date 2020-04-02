package com.example.springsophsoft.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;

public class personTransaction extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent);


        mListView = (ListView) findViewById(R.id.sentListView);

        final TransactionHelper transactiondb = new TransactionHelper(this);
        Intent i = getIntent();
        final String Person_SendingTo = i.getStringExtra("Person_SendingTo");

        Cursor data = transactiondb.getDataPerson(LogIn.getString(), Person_SendingTo);
        transactionList(data);
    }
    private void transactionList(Cursor data){

        ArrayList<Transaction> listData = new ArrayList<>();
        double intamount = 0;

        while (data.moveToNext()){
            Transaction mytransaction = new Transaction("recieverid", "senderid", "amount","reason", "T");

            mytransaction.setRecieverid(data.getString(3));
            mytransaction.setSenderid(data.getString(2));
            mytransaction.setAmount(data.getString(1));
            intamount+= Double.parseDouble(data.getString(1));
            mytransaction.setReason(data.getString(4));
            mytransaction.setDate(data.getString(5));
            listData.add(mytransaction);
        }
        Collections.reverse(listData);

        TextView amount = (TextView) findViewById(R.id.amountSentTextView);
        String stringamount = "$" + Double.toString(intamount);
        amount.setText(stringamount);

        TransactionListAdapter adapter = new TransactionListAdapter(listData, this);
        mListView.setAdapter(adapter);
    }
}

