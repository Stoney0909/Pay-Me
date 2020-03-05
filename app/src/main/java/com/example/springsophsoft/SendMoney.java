package com.example.springsophsoft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springsophsoft.Helper.SendingHelper;

public class SendMoney extends AppCompatActivity {
    SendingHelper db;
     EditText Amount,
             Comment;
     Button Send;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        Amount = (EditText)findViewById(R.id.Amount_Sending);
        Comment = (EditText)findViewById(R.id.Sending_Message);
        Send=(Button)findViewById(R.id.Send);
        db =new SendingHelper(this);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent i = getIntent();
                String Person_SendingTo = i.getStringExtra("Person_SendingTo");
                String amount = Amount.getText().toString();
                String message = Comment.getText().toString();
                boolean insert = db.add(LogIn.getString(),Person_SendingTo, amount,message);
                if (insert) {
                    toastMessage("You successfully send money");
                    Homepage();
                }
                else toastMessage("Something went wrong");
            }
            });
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void Homepage()
    {
        Intent intent = new Intent(getApplication(), HomePage.class);
        startActivity(intent);
    }
}
