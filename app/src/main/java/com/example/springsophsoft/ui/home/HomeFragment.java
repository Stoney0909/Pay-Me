package com.example.springsophsoft.ui.home;

import android.content.Intent;
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
import com.example.springsophsoft.HomePage;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button recievedbtn = (Button)root.findViewById(R.id.recievedBtn);
        Button sentbtn = (Button)root.findViewById(R.id.sentBtn);

        recievedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recieved();
            }
        });
        sentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sent();
            }
        });


        return root;
    }
private void recieved(){
    Intent intent = new Intent(getActivity(), Recieved.class);
    startActivity(intent);
}
private void sent(){
        Intent intent = new Intent(getActivity(), Sent.class);
        startActivity(intent);
    }




}

