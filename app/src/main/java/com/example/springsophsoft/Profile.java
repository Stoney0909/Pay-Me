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
    // private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mAuth = FirebaseAuth.getInstance();
        db = new Databasehelper(this);

        Update=(Button) findViewById(R.id.Update);
        UsernameUpdateText=(EditText) findViewById(R.id.UsernameUpdateText);
        EmailUpdateText =(EditText) findViewById(R.id.EmailUpdateText);
        PhoneUpdateText=(EditText) findViewById(R.id.PhoneUpdateText);
        FirstNameText =(EditText) findViewById(R.id.FirstNameText);
        LastNameText=(EditText) findViewById(R.id.LastNameText);
        //    final String getID = mAuth.getCurrentUser().getProviderId();

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername = UsernameUpdateText.getText().toString();
                String firstName = FirstNameText.getText().toString();
                String lastName = LastNameText.getText().toString();
                String Phone = PhoneUpdateText.getText().toString();
                String Email= EmailUpdateText.getText().toString();

                //  db.UpdateUserDetails(susername,Email,Integer.parseInt(getID));
                db.Update(LogIn.getString(),susername,Email,firstName,lastName,Phone);
//              boolean me= db.AdditionalInfo(firstName,lastName,Phone);
//
//
//                if (me==true) {
//                    toastMessage("You add Try it");
//                } else {
//                    toastMessage("Wrong Email or Password");
//                }
            }
        });
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}