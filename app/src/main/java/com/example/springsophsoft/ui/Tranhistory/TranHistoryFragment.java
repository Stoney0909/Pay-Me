package com.example.springsophsoft.ui.Tranhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.R;

public class TranHistoryFragment extends Fragment {

    private TranHistoryViewModel tranHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tranHistoryViewModel =
                ViewModelProviders.of(this).get(TranHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tran_history, container, false);
        final TextView textView = root.findViewById(R.id.text_tranHistory);
        tranHistoryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}