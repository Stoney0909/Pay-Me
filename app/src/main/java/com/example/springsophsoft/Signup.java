package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private EditText Username, email, password, confirmPassword;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Username = (EditText)findViewById(R.id.Username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                final String name = Username.getText().toString();
                final String Uemail = email.getText().toString();
                final String Upassword = password.getText().toString();
                final String UconfirmP = confirmPassword.getText().toString();

                if(name.length() == 0) {
                    Username.requestFocus();
                    Username.setError("FIELD CANNOT BE EMPTY");
                }
                if(!name.matches("[a-zA-Z ]+"))
                {
                    Username.requestFocus();
                    Username.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                if(!Uemail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
                {
                    email.requestFocus();
                    email.setError("Not a validate");
                }
                if(Upassword.length() < 8)
                {
                    password.requestFocus();
                    password.setError("The password have to be more than 8 character");
                }
                if(!UconfirmP.equals(Upassword))
                {
                    confirmPassword.requestFocus();
                    confirmPassword.setError("The password don't match");
                }
//                else
//                {
//                    Toast.makeText(Signup.this,"Validation Successful",Toast.LENGTH_LONG).show();
//                }
//            }
//          }
//       );
    }
}
