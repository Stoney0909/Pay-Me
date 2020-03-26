package com.example.springsophsoft;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.Notification.NotificationViewModel;
import com.example.springsophsoft.ui.home.NotificationListAdapter;
import com.example.springsophsoft.ui.home.Transaction;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;

public class historyMessage extends AppCompatActivity {


    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent);



        mListView = (ListView) findViewById(R.id.sentListView);

        final TransactionHelper transactiondb = new TransactionHelper(this);

        Cursor data = transactiondb.getData(LogIn.getString());
        transactionList(data);
    }
    private void transactionList(Cursor data){

        ArrayList<Transaction> listData = new ArrayList<>();
        double intamount = 0;

        while (data.moveToNext()){
            Transaction mytransaction = new Transaction("recieverid", "senderid", "amount","reason", "T");

            mytransaction.setRecieverid(data.getString(2));
          //  mytransaction.setSenderid(data.getString(2));
            mytransaction.setSenderid(data.getString(1));
            intamount+= Double.parseDouble(data.getString(1));
            mytransaction.setReason(data.getString(4));
            mytransaction.setDate(data.getString(5));
            listData.add(mytransaction);
        }
        Collections.reverse(listData);

     //   TextView amount = (TextView) findViewById(R.id.amountSentTextView);
      //  String stringamount = "$" + Double.toString(intamount);
       // amount.setText(stringamount);

        NotificationListAdapter adapter = new NotificationListAdapter(listData, this);
        mListView.setAdapter(adapter);
    }
}
