package com.example.springsophsoft.ui.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.AddCash.AddCashFragment;
import com.example.springsophsoft.ui.home.HomeFragment;

public class ChangePassword extends AppCompatActivity {

    Databasehelper mydb;
    private EditText Opass, Npass, CNpass;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mydb = new Databasehelper(this);

        Opass = (EditText)findViewById(R.id.oldPass);
        Npass = (EditText)findViewById(R.id.NewPass);
        CNpass = (EditText)findViewById(R.id.CNpass);
        submit = (Button)findViewById(R.id.btnChangePassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });


    }

    public void change()
    {
        String OldPassword = Opass.getText().toString();
        String NewPassword = Npass.getText().toString();
        String ConFNewPass = CNpass.getText().toString();
        String DATAPASSWORD = mydb.getPassword();

        if(OldPassword.equals("") || NewPassword.equals("") || ConFNewPass.equals(""))
        {
            toastMessage("All the fields need a input");
        }
        else
        {
            if(!OldPassword.equals(DATAPASSWORD))
            {
                toastMessage("Old password incorrect");
            }
            else if(NewPassword.equals(DATAPASSWORD))
            {
                toastMessage("New password can not be the old password");
            }
            else if(!NewPassword.equals(ConFNewPass))
            {
                toastMessage("Password don't match");
            }
            else
            {
                mydb.updatePassword(ConFNewPass);
                toastMessage("Password changed success");
            }
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
