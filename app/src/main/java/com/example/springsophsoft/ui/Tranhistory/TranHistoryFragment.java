package com.example.springsophsoft.ui.Tranhistory;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.Transaction;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TranHistoryFragment extends Fragment {

    TransactionHelper db;
    private TextView mess;
    private TranHistoryViewModel tranHistoryViewModel;
    private SwipeMenuListView mListView;
    private Button Re, search, sent;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tranHistoryViewModel =
                ViewModelProviders.of(this).get(TranHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tran_history, container, false);

        Re = (Button) root.findViewById(R.id.btn_recieved);
        search = (Button) root.findViewById(R.id.btn_searchButton);
        sent = (Button) root.findViewById(R.id.btn_sent);
        mListView = (SwipeMenuListView) root.findViewById(R.id.List_wholeTransactionListView);
        mess = (TextView) root.findViewById(R.id.A_Message);

        TextView amount = (TextView) root.findViewById(R.id.amountSentTextView23);
        TextView sentorrec = (TextView) root.findViewById(R.id.textView61);


        db = new TransactionHelper(getActivity());
        Cursor data = db.getAllData(LogIn.getString());

        transactionList(data, amount, sentorrec);


        Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recieving();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searching();
            }
        });

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senting();
            }
        });
        return root;
    }

    private void transactionList(Cursor data,TextView amount ,TextView sentorrec) {

        final ArrayList<Transaction> listData = new ArrayList<>();
        double intamount = 0;

        if(data.getCount() == 0)
        {
            mess.setText("No transaction history");
            sentorrec.setText("");
        }
        else
        {
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



                if (intamount > 0){
                    sentorrec.setText("recieved:");
                    String stringamount = "$" + Double.toString(intamount);
                    amount.setText(stringamount);
                }
                else if (intamount < 0){
                    String stringamount = "$" + Double.toString(intamount* -1);
                    amount.setText(stringamount);
                }
            }
            Collections.reverse(listData);



            TransactionListAdapter adapter = new TransactionListAdapter(listData, getActivity());
            mListView.setAdapter(adapter);

            SwipeMenuCreator creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // create "delete" item
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getActivity().getApplicationContext());
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
                    switch (index)
                    {
                        case 0:
                        toastMessage("Prosition: " + position);
                        db.DeleteEntry(listData.get(position).getSenderid(), listData.get(position).getAmount(), listData.get(position).getReason(), listData.get(position).getRecieverid(), listData.get(position).getDate());
                        
                    }
                    return true;
                }
            });

        }
    }



    public void recieving()
    {
        Intent intent = new Intent(getActivity(), Recieved.class);
        startActivity(intent);
    }

    public void searching()
    {
        Intent intent = new Intent(getActivity(), SearchTransaction.class);
        startActivity(intent);
    }

    public void senting()
    {
        Intent intent = new Intent(getActivity(), Sent.class);
        startActivity(intent);
    }

    public void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}