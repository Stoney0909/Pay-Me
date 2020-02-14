package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.springsophsoft.Helper.Databasehelper;
//import com.example.springsophsoft.helper.Databasehelper;

public class Signup extends AppCompatActivity {

    private static String TAG = "Signup";
    Databasehelper mDatabasehelper;
    private EditText password, username, email, confirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnRegister = (Button) findViewById(R.id.signup);
        password = (EditText) findViewById(R.id.passwordTextView);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        username = (EditText) findViewById(R.id.Username);
        email = (EditText) findViewById(R.id.email);
        mDatabasehelper = new Databasehelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString();

                if (password.length() == 0) {
                    toastMessage("You must enter something in the Password.");
                }
                else if (username.length() == 0){
                    toastMessage("You must enter something in the Username.");
                }
                else if (email.length() == 0){
                    toastMessage("You must enter something in the Email.");
                }
                else if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                    toastMessage("Passwords do not match.");
                }
                else{
                    boolean didAdd = mDatabasehelper.addData(Username);
                    if (didAdd)toastMessage("Success!");
                    else toastMessage("Failure");
                }

            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
