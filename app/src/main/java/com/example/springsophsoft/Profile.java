package com.example.springsophsoft;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.springsophsoft.Helper.Databasehelper;


public class Profile extends AppCompatActivity {
    Databasehelper db;
    private EditText UsernameUpdateText, EmailUpdateText, PhoneUpdateText, FirstNameText,LastNameText;
    private Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new Databasehelper(this);

        Update=(Button) findViewById(R.id.btn_Update);
        UsernameUpdateText=(EditText) findViewById(R.id.UsernameT);
        EmailUpdateText =(EditText) findViewById(R.id.EmailT);
        PhoneUpdateText=(EditText) findViewById(R.id.PhoneT);
        FirstNameText =(EditText) findViewById(R.id.FirstNameT);
        LastNameText=(EditText) findViewById(R.id.LastNameT);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername = UsernameUpdateText.getText().toString();
                String firstName = FirstNameText.getText().toString();
                String lastName = LastNameText.getText().toString();
                String Phone = PhoneUpdateText.getText().toString();
                String Email= EmailUpdateText.getText().toString();

                if(susername.equals("") && firstName.equals("") && lastName.equals("") && Phone.equals("") && Email.equals(""))
                {
                    toastMessage("Profile Unchanged");
                }
                else
                {
                    if(susername.equals(""))
                    {
                        susername = LogIn.getString();
                    }
                    if(firstName.equals(""))
                    {
                        firstName = db.getFirstName();
                    }
                    if(lastName.equals(""))
                    {
                        lastName = db.getLastName();
                    }
                    if(Phone.equals(""))
                    {
                        Phone = db.getPhone();
                    }
                    if( Email.equals(""))
                    {
                        Email = db.getEmail();
                    }




                    db.Update(LogIn.getString(),susername,Email,firstName,lastName,Phone);
                    toastMessage("Update success");
                    UsernameUpdateText.setText("");
                    FirstNameText.setText("");
                    LastNameText.setText("");
                    PhoneUpdateText.setText("");
                    EmailUpdateText.setText("");
                }
        }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}