package com.example.springsophsoft.ui.signUpAndLogIn;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.springsophsoft.Helper.Databasehelper;
        import com.example.springsophsoft.HomePage;
        import com.example.springsophsoft.R;
        import com.example.springsophsoft.ui.home.User;

public class LogIn extends AppCompatActivity {

        Databasehelper db;
        private Button login;
        private EditText username, password;
        public static String user;
        User myuser;

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
                    user = susername;
                boolean chkuserpass = db.chkusernamepassword(susername, spassword);
                if (chkuserpass) {
                    Homepage();
                }
                else toastMessage("Wrong Email or Password");
                }
            });
        }
        private void Homepage()
        {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

        private void toastMessage(String message){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    public static String getString() {
        return user;
    }
    }
