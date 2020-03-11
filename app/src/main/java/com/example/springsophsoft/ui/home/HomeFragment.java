package com.example.springsophsoft.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.Global;
import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.Transaction;
import com.example.springsophsoft.TransactionListAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button addtransaction;
    private ListView mListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mListView = (ListView)root.findViewById(R.id.TransactionListView);

        final TransactionHelper transactiondb = new TransactionHelper(getActivity());
        final Databasehelper userdb = new Databasehelper(getActivity());


        Global.username = getActivity().getIntent().getStringExtra("id");
        

        Cursor data = transactiondb.getData(Global.username);
        transactionList(data);
        return root;
    }


    private void transactionList(Cursor data){

        ArrayList<Transaction> listData = new ArrayList<>();
        Transaction mytransaction = new Transaction("recieverid", "senderid", "amount","reason", "T");
        while (data.moveToNext()){

            mytransaction.setRecieverid(data.getString(1));
            mytransaction.setSenderid(data.getString(2));
            mytransaction.setAmount(data.getString(3));
            mytransaction.setReason(data.getString(4));
            mytransaction.setDate(data.getString(5));
            listData.add(mytransaction);
        }

        TransactionListAdapter adapter = new TransactionListAdapter(listData, getActivity());
        mListView.setAdapter(adapter);
    }


}

