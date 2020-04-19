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

        TextView balance = (TextView)root.findViewById(R.id.balanceTextView);
        TextView email = (TextView) root.findViewById(R.id.EmailViewHome);
        TextView phone = (TextView) root.findViewById(R.id.PhoneViewHome);
        Databasehelper databasehelper = new Databasehelper(getActivity());
        String amount = "$" + databasehelper.getBalance();
        balance.setText(amount);
        String Email = databasehelper.getEmail();
        email.setText(Email);
        Databasehelper db = new Databasehelper(getActivity());
        TextView nameTextView = (TextView)root.findViewById(R.id.nameTextView);
        String name = db.getFirstName() + " " + db.getLastName();
        String PhoneNumber = db.getPhone();

        if(db.getPhone() == null && db.getPhone() == null)
        {
            phone.setText("None");
        }
        else if(db.getPhone().equals("null") && db.getPhone().equals("null"))
        {
            phone.setText("None");
        }
        else if(db.getPhone() != null && db.getPhone() != null)
        {
            phone.setText(PhoneNumber);
        }



        if(db.getFirstName() == null && db.getLastName() == null)
        {
            nameTextView.setText("there");
        }
        else if(db.getFirstName().equals("null") && db.getLastName().equals("null"))
        {
            nameTextView.setText("there");
        }
        else if(db.getFirstName() != null && db.getLastName() != null)
        {
            nameTextView.setText(name);
        }



        return root;
    }
}

