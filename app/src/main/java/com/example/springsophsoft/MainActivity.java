package com.example.springsophsoft;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
private Button regisiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regisiter = (Button) findViewById(R.id.regisiter);

        regisiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    public void Register()
    {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
