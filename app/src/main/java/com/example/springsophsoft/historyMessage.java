package com.example.springsophsoft;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.springsophsoft.HomePage;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.Notification.NotificationViewModel;
import com.example.springsophsoft.ui.home.NotificationListAdapter;
import com.example.springsophsoft.ui.home.Transaction;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;



public class historyMessage extends AppCompatActivity {

    private static final String TAG = "historyMessage";
    private SwipeMenuListView mListView;
    TransactionHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mydb = new TransactionHelper(this);


        mListView = (SwipeMenuListView) findViewById(R.id.sentListView);

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
                        Intent intent = new Intent(historyMessage.this, SendMoney.class);
                        intent.putExtra("Person_SendingTo",name);
                        recreate();
                        startActivity(intent);
                        mydb.Delete(name,amount);
                        break;
                    case 1:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        String name2 = listData.get(position).getRecieverid();
                        String amount2= listData.get(position).getSenderid();
                        toastMessage("You successfully denied");
                        mydb.Delete(name2,amount2);
                        recreate();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
