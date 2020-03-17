package com.example.springsophsoft.ui.Balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.signUpAndLogIn.MainActivity;

public class BalanceFragment extends Fragment {

    private BalanceViewModel balanceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        balanceViewModel = ViewModelProviders.of(this).get(BalanceViewModel.class);
        getout();
        View root = inflater.inflate(R.layout.fragment_balance, container, false);
        return root;
    }

    public void getout()
    {
       Intent intent = new Intent(getActivity(), MainActivity.class);
       startActivity(intent);
    }
}