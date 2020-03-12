package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.Databasehelper;

public class SignUp extends AppCompatActivity {

    private static String TAG = "Signup";
    Databasehelper mDatabasehelper;
    private EditText password, username, email, confirmPassword;
    private Button btnRegister;
    int balance = 3000;
    public static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegister = (Button) findViewById(R.id.signup);
        password = (EditText) findViewById(R.id.passwordText);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        username = (EditText) findViewById(R.id.UsernameText);
        email = (EditText) findViewById(R.id.emailText);
        mDatabasehelper = new Databasehelper(this);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername = username.getText().toString();
                String semail = email.getText().toString();
                String spassword = password.getText().toString();
                String sconfirmpassword = confirmPassword.getText().toString();
                user = susername;

                if (susername.equals("")|| semail.equals("")|| spassword.equals(""))
                {
                    toastMessage("Fields are Empty");
                }
                else{
                    if(spassword.equals(sconfirmpassword)){
                        boolean chkemail = mDatabasehelper.chkemail(semail);
                        if (chkemail){
                            boolean chkusername = mDatabasehelper.chkusername(susername);
                            if (chkusername){
                                boolean insert = mDatabasehelper.addData(susername, semail, sconfirmpassword, balance);
                                if (insert){
                                    toastMessage("Register Successfull");
                                    login();
                                }
                                else {
                                    toastMessage("Register Failure");
                                }
                            }
                            else {
                                toastMessage("Username is taken");
                            }
                        }
                        else{
                            toastMessage("Email already exists.");
                        }

                    }
                    else{
                        toastMessage("Password and Confirm Password do not Match");
                    }
                }

            }
        });
    }

    public static String getUsername()
    {
        String name;
        name = user;
        return name;
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void login()
    {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}
