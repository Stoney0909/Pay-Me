package com.example.springsophsoft.ui.Tranhistory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.Transaction;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;

public class personTransaction extends AppCompatActivity {
    private ListView mListView;
    String Person_SendingTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent);


        mListView = (ListView) findViewById(R.id.sentListView);

        final TransactionHelper transactiondb = new TransactionHelper(this);
        Intent i = getIntent();
        Person_SendingTo = i.getStringExtra("Person_SendingTo");

        Cursor data = transactiondb.getDataPerson(LogIn.getString(), Person_SendingTo);
        transactionList(data);
    }
    private void transactionList(Cursor data){

        ArrayList<Transaction> listData = new ArrayList<>();
        double intamount = 0;

        while (data.moveToNext()){
            Transaction mytransaction = new Transaction("recieverid", "senderid", "amount","reason", "T");
            if (data.getString(3).equals(Person_SendingTo))
            {
                if (data.getString(2).equals(LogIn.getString())) {
                    mytransaction.setRecieverid(data.getString(3));
                    mytransaction.setSenderid(data.getString(2));
                    mytransaction.setAmount(data.getString(1));
                    intamount += Double.parseDouble(data.getString(1));
                    mytransaction.setReason(data.getString(4));
                    mytransaction.setDate(data.getString(5));
                    listData.add(mytransaction);
                }
            }
            if (data.getString(2).equals(Person_SendingTo)){
                if (data.getString(3).equals(LogIn.getString()))
                {
                    mytransaction.setRecieverid(data.getString(3));
                    mytransaction.setSenderid(data.getString(2));
                    mytransaction.setAmount(data.getString(1));
                    intamount-= Double.parseDouble(data.getString(1));
                    mytransaction.setReason(data.getString(4));
                    mytransaction.setDate(data.getString(5));
                    listData.add(mytransaction);
                }

            }


        }
        Collections.reverse(listData);
        TextView amount = (TextView) findViewById(R.id.amountSentTextView);
        TextView sentorrec = (TextView) findViewById(R.id.textView6);
        if (intamount > 0){
            sentorrec.setText("You have recieved:");
            String stringamount = "$" + Double.toString(intamount);
            amount.setText(stringamount);
        }
        else if (intamount < 0){
            String stringamount = "$" + Double.toString(intamount* -1);
            amount.setText(stringamount);
        }
        else{
            sentorrec.setText("No transaction history");
        }

        TransactionListAdapter adapter = new TransactionListAdapter(listData, this);
        mListView.setAdapter(adapter);
    }
}

