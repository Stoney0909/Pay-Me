package com.example.springsophsoft.ui.signUpAndLogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.R;
import com.example.springsophsoft.ui.signUpAndLogIn.ReSetPassword;

public class ForgetPassword extends AppCompatActivity {

    private EditText email;
    private Button sending;
    Databasehelper db;
    public static String getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = (EditText)findViewById(R.id.REPSemail);
        sending = (Button)findViewById(R.id.SendEmail);
        db = new Databasehelper(this);
        sending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                getEmail = Email;
                boolean chkemail = db.chkemail(Email);

                if (Email.length() == 0) {
                    toastMessage("PLease enter email");
                }
                else
                {
                    if (!Email.matches(emailPattern)) {
                        toastMessage("Invalid email");
                        email.setText("");
                    }
                    else
                    {
                        if (chkemail == false)
                        {
                            success();

                        }
                        else {
                            toastMessage("Undefined email");
                            email.setText("");
                        }
                    }
                }

            }
        });

    }

    public static String ReturnEMail()
    {
        return getEmail;
    }

    public void success()
    {
        Intent intent = new Intent(this, ReSetPassword.class);
        startActivity(intent);
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
