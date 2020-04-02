package com.example.springsophsoft;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_notification);



        mListView = (ListView) findViewById(R.id.sentListView);

        final TransactionHelper transactiondb = new TransactionHelper(this);

        Cursor data = transactiondb.getData(LogIn.getString());
        transactionList(data);
    }
    private void transactionList(Cursor data){

        final ArrayList<Transaction> listData = new ArrayList<>();
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = listData.get(position).getRecieverid();
                String amount= listData.get(position).getSenderid();
//                toastMessage(name);
//                toastMessage(amount);
                open(name,amount);
//                toastMessage(val);
//                String text = userlist.getItemAtPosition(position).toString();
//                Intent intent = new Intent(ge, Accept_Decline.class);
//                intent.putExtra("Person_SendingTo",text);
//                startActivity(intent);
            }
        });
    }
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void open(String name, String amount)
    {
        Intent i = new Intent(this, Accept_Decline.class);
        i.putExtra("Demandeur",name);
        i.putExtra("Montant",amount);
        startActivity(i);
    }
}
