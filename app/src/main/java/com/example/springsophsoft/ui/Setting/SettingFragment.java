package com.example.springsophsoft.ui.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.springsophsoft.LogIn;
import com.example.springsophsoft.MainActivity;
import com.example.springsophsoft.Profile;
import com.example.springsophsoft.R;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private Button signout, profile;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        signout = (Button)root.findViewById(R.id.btnLogout);
        profile = (Button)root.findViewById(R.id.btnProfile);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile();
            }
        });
        return root;
    }

    public void signout()
    {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
    public void profile()
    {
        Intent intent = new Intent(getActivity(), Profile.class);
        startActivity(intent);
    }
}