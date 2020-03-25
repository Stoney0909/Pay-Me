package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;

import java.security.KeyStore;

public class ReSetPassword extends AppCompatActivity {

    Databasehelper db;
    private EditText ResetP, CResetP;
    private Button update;
    String email = ForgetPassword.getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_set_password);

        db = new Databasehelper(this);

        ResetP = (EditText)findViewById(R.id.Reseting);
        CResetP = (EditText)findViewById(R.id.CReseting);
        update = (Button)findViewById(R.id.btn_ResetPassword);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RP = ResetP.getText().toString();
                String CRP = CResetP.getText().toString();
                if(RP == "" || CRP == "")
                {
                    toastMessage("Please enter all the fields");
                }
                else
                {
                    if(!RP.equals(CRP))
                    {
                        toastMessage("The password don't match");
                    }
                    else
                    {
                        db.REupdatePassword(CRP, email);
                        toastMessage("Password changed success");
                        goback();
                    }
                }
            }
        });

    }

    public void goback()
    {
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
