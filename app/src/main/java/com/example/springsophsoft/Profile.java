package com.example.springsophsoft;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.springsophsoft.Helper.Databasehelper;
import com.example.springsophsoft.ui.signUpAndLogIn.LogIn;


public class Profile extends AppCompatActivity {
    Databasehelper db;
    private EditText UsernameUpdateText, EmailUpdateText, PhoneUpdateText, FirstNameText,LastNameText;
    private Button Update;
    boolean userC = false, emailC = false, phoneC = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new Databasehelper(this);

        Update=(Button) findViewById(R.id.btn_Update);
       // UsernameUpdateText=(EditText) findViewById(R.id.UsernameT);
        EmailUpdateText =(EditText) findViewById(R.id.EmailT);
        PhoneUpdateText=(EditText) findViewById(R.id.PhoneT);
        FirstNameText =(EditText) findViewById(R.id.FirstNameT);
        LastNameText=(EditText) findViewById(R.id.LastNameT);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername = UsernameUpdateText.getText().toString();;
                String firstName = FirstNameText.getText().toString();
                String lastName  = LastNameText.getText().toString();;
                String Phone = PhoneUpdateText.getText().toString();;
                String Email =  EmailUpdateText.getText().toString();;

                if(susername.equals("") && firstName.equals("") && lastName.equals("") && Phone.equals("") && Email.equals(""))
                {
                    toastMessage("Profile Unchanged");
                }
                else
                {

//                    if(susername.equals(""))
//                    {
//                        susername = LogIn.getString();
//                        userC = true;
//                    }
//                    else
//                    {
//                        boolean chkusername = db.chkusername(susername);
//                        if (chkusername == false){
//                            toastMessage("Username is taken");
//                            UsernameUpdateText.setText("");
//                            userC = false;
//                        }
//                        else
//                        {
//                            susername = UsernameUpdateText.getText().toString();
//                            userC = true;
//                        }
//
//                    }

                    if(firstName.equals(""))
                    {
                        firstName = db.getFirstName();
                    }
                    else
                    {
                        firstName = FirstNameText.getText().toString();
                    }

                    if(lastName.equals(""))
                    {
                        lastName = db.getLastName();
                    }
                    else
                    {
                        lastName  = LastNameText.getText().toString();
                    }

                    if(Phone.equals(""))
                    {
                        Phone = db.getPhone();
                        phoneC = true;
                    }
                    else
                    {
                        if(Phone.length() != 10)
                        {
                            toastMessage("PLease enter the correct phone number");
                            PhoneUpdateText.setText("");
                            phoneC = false;
                        }
                        else
                        {
                            Phone = PhoneUpdateText.getText().toString();
                            phoneC = true;
                        }
                    }

                    if( Email.equals(""))
                    {
                        Email = db.getEmail();
                        emailC = true;
                    }
                    else
                    {
                        boolean chkemail = db.chkemail(Email);
                        if(chkemail == false)
                        {
                            toastMessage("Email already exists.");
                            EmailUpdateText.setText("");
                            emailC = false;
                        }
                        else
                        {
                            Email =  EmailUpdateText.getText().toString();
                            emailC = true;
                        }

                    }

                    if(phoneC == true && emailC == true)
                    {
                        db.Update(LogIn.getString(),susername,Email,firstName,lastName,Phone);
                        toastMessage("Update success");
                        UsernameUpdateText.setText("");
                        FirstNameText.setText("");
                        LastNameText.setText("");
                        PhoneUpdateText.setText("");
                        EmailUpdateText.setText("");
                        Homepage();
                    }





                }
        }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void Homepage()
    {
        Intent intent = new Intent(getApplication(), HomePage.class);
        startActivity(intent);
    }
}