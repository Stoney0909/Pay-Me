package com.example.springsophsoft.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.Helper.TransactionHelper;
import com.example.springsophsoft.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button transactoionsbtn = (Button)root.findViewById(R.id.transactbtn);
        TextView balance = (TextView)root.findViewById(R.id.balanceTextView);
        Databasehelper databasehelper = new Databasehelper(getActivity());
        String amount = "$" + databasehelper.getBalance();
        balance.setText(amount);

        transactoionsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction();
            }
        });
        Databasehelper db = new Databasehelper(getActivity());
        TextView nameTextView = (TextView)root.findViewById(R.id.nameTextView);
        String name = db.getFirstName() + " " + db.getLastName();
        nameTextView.setText(name);




        return root;
    }
private void transaction(){
    Intent intent = new Intent(getActivity(), wholeTransactionList.class);
    startActivity(intent);
}





}

