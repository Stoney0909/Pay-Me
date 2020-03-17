package com.example.springsophsoft.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

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
        addtransaction = (Button)root.findViewById(R.id.addtransactionbutton);
        addtransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactiondb.addData(100, LogIn.getString(), "Reciever", "Test String", "3/12/2020");
            }
        });

        

        Cursor data = transactiondb.getData(LogIn.getString());
        transactionList(data);

        return root;
    }


    private void transactionList(Cursor data){

        ArrayList<Transaction> listData = new ArrayList<>();

        while (data.moveToNext()){
            Transaction mytransaction = new Transaction("recieverid", "senderid", "amount","reason", "T");

            mytransaction.setRecieverid(data.getString(3));
            mytransaction.setSenderid(data.getString(2));
            mytransaction.setAmount(data.getString(1));
            mytransaction.setReason(data.getString(4));
            mytransaction.setDate(data.getString(5));
            listData.add(mytransaction);
        }

        TransactionListAdapter adapter = new TransactionListAdapter(listData, getActivity());
        mListView.setAdapter(adapter);
    }


}

