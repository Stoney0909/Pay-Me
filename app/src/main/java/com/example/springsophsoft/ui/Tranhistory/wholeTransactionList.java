package com.example.springsophsoft.ui.Tranhistory;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.Transaction;
import com.example.springsophsoft.ui.Notification.historyMessage;
import com.example.springsophsoft.ui.Search.SendMoney;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;

public class wholeTransactionList extends AppCompatActivity {
    private SwipeMenuListView mListView;
    private static final String TAG = "wholeTransactionList";
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

        mListView = (SwipeMenuListView) findViewById(R.id.wholeTransactionListView);

        final TransactionHelper transactiondb = new TransactionHelper(this);

        Cursor data = transactiondb.getAllData(LogIn.getString());
        transactionList(data);
    }

    private void transactionList(Cursor data) {

        final ArrayList<Transaction> listData = new ArrayList<>();
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
        mListView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Send");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_denied);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        String name = listData.get(position).getRecieverid();
                        String amount= listData.get(position).getSenderid();
                        Intent intent = new Intent(wholeTransactionList.this, SendMoney.class);
                        intent.putExtra("Person_SendingTo",name);
                        recreate();
                        startActivity(intent);
                     //   mydb.Delete(name,amount);
                        break;
                    case 1:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        String name2 = listData.get(position).getRecieverid();
                        String amount2= listData.get(position).getSenderid();
                     //   toastMessage("You successfully denied");
                     //   mydb.Delete(name2,amount2);
                        recreate();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

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