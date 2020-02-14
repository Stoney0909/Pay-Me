package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;

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
                AddData(username.getText().toString(), email.getText().toString(), password.getText().toString());

            }
        });

    }



    public void AddData(String username, String email, String password){
        boolean insertData = mDatabasehelper.addData(username, email, password);
        if (insertData)
        {
            toastMessage("Data Successfully Inserted");
        }
        else
        {
            toastMessage("Something went Wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
