package com.example.springsophsoft;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.springsophsoft.Helper.Databasehelper;

public class LogIn extends AppCompatActivity {

        Databasehelper db;
        private Button login;
        private EditText username, password;
        public User myuser;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_log_in);

            login = (Button) findViewById(R.id.btnLogIn);
            username = (EditText) findViewById(R.id.usernameTextView);
            password = (EditText) findViewById(R.id.passwordTextView);
            db = new Databasehelper(this);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                String susername = username.getText().toString();
                String spassword = password.getText().toString();

                boolean chkuserpass = db.chkusernamepassword(susername, spassword);
                if (chkuserpass)toastMessage("Log in successfull");
                else toastMessage("Wrong Email or Password");

                myuser.setUsername(susername);
                myuser.setPassword(spassword);
                }
            });






        }
        private void toastMessage(String message){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }
