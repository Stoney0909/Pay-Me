package com.example.springsophsoft.ui.Tranhistory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.Transaction;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;
import java.util.Collections;

public class TranHistoryFragment extends Fragment {

    TransactionHelper db;
    private TextView mess;
    private TranHistoryViewModel tranHistoryViewModel;
    private ListView mListView;
    private Button Re, search, sent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tranHistoryViewModel =
                ViewModelProviders.of(this).get(TranHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tran_history, container, false);

        Re = (Button) root.findViewById(R.id.btn_recieved);
        search = (Button) root.findViewById(R.id.btn_searchButton);
        sent = (Button) root.findViewById(R.id.btn_sent);
        mListView = (ListView) root.findViewById(R.id.List_wholeTransactionListView);
        mess = (TextView) root.findViewById(R.id.A_Message);

        db = new TransactionHelper(getActivity());
        Cursor data = db.getAllData(LogIn.getString());
        transactionList(data);


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

    private void transactionList(Cursor data) {

        ArrayList<Transaction> listData = new ArrayList<>();
        double intamount = 0;

        if(data.getCount() == 0)
        {
            mess.setText("No transaction history");
        }
        else
        {
            while (data.moveToNext()) {
                Transaction mytransaction = new Transaction("recieverid", "senderid", "amount", "reason", "T");

                mytransaction.setRecieverid(data.getString(3));
                mytransaction.setSenderid(data.getString(2));
                mytransaction.setAmount(data.getString(1));
                intamount += Double.parseDouble(data.getString(1));
                mytransaction.setReason(data.getString(4));
                mytransaction.setDate(data.getString(5));
                listData.add(mytransaction);
            }
            Collections.reverse(listData);

            TransactionListAdapter adapter = new TransactionListAdapter(listData, getActivity());
            mListView.setAdapter(adapter);
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

}