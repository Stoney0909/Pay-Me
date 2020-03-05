package com.example.springsophsoft.ui.home;

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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

        mListView = (ListView)root.findViewById(R.id.transactionListView);
        addtransaction = (Button)root.findViewById(R.id.addTransactionbtn);
        final TransactionHelper transactiondb = new TransactionHelper(getActivity());
        Databasehelper userdb = new Databasehelper(getActivity());

        final String id = getActivity().getIntent().getStringExtra("id");

        addtransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              transactiondb.addData(1, id, "1", "First com.example.springsophsoft.Transaction", "0");
            }
        });
        Cursor data = transactiondb.getData(id);
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

        TransactionListAdapter adapter = new TransactionListAdapter(getActivity(), R.layout.content_home_page, listData);
        mListView.setAdapter(adapter);
    }
}
