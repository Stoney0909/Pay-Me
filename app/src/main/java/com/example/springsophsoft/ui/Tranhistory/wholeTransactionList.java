package com.example.springsophsoft.ui.Tranhistory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.Transaction;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;

public class wholeTransactionList extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wholetransactionlist);
        Button sentButton = (Button) findViewById(R.id.sentBtn);

        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sent();
            }
        });

        Button recievedButton = (Button) findViewById(R.id.recievedbtn);

        recievedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recieved();
            }
        });

        final Button searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        mListView = (ListView) findViewById(R.id.wholeTransactionListView);

        final TransactionHelper transactiondb = new TransactionHelper(this);

        Cursor data = transactiondb.getAllData(LogIn.getString());
        transactionList(data);
    }

    private void transactionList(Cursor data) {

        ArrayList<Transaction> listData = new ArrayList<>();
        double intamount = 0;

        while (data.moveToNext()){
            Transaction mytransaction = new Transaction("recieverid", "senderid", "amount","reason", "T");

                if (data.getString(2).equals(LogIn.getString())) {
                    mytransaction.setRecieverid(data.getString(3));
                    mytransaction.setSenderid(data.getString(2));
                    mytransaction.setAmount(data.getString(1));
                    intamount += Double.parseDouble(data.getString(1));
                    mytransaction.setReason(data.getString(4));
                    mytransaction.setDate(data.getString(5));
                    listData.add(mytransaction);
                }

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

    private void recieved(){
        Intent intent = new Intent(this, Recieved.class);
        startActivity(intent);
    }
    private void sent(){
        Intent intent = new Intent(this, Sent.class);
        startActivity(intent);
    }
    private void search(){
        Intent intent = new Intent(this, SearchTransaction.class);
        startActivity(intent);
    }
}