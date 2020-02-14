package com.example.springsophsoft;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.springsophsoft.Helper.Databasehelper;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private Button register;
    private Button logIn;

    private VideoView videoBG;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
//
//      Databasehelper mDatabasehelper;
//    private TextView textView, anotherText;
//    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.btnRegisiter);
        logIn = (Button) findViewById(R.id.btn_login);
        videoBG = (VideoView) findViewById(R.id.IntroVideoView);



//        mDatabasehelper = new Databasehelper(this);
//        button = (Button) findViewById(R.id.button);
//        textView = (TextView) findViewById(R.id.textView);
//        anotherText = (TextView)findViewById(R.id.textView2) ;
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddData(textView.getText().toString(), anotherText.getText().toString());
//            }
//        });
//








        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback);

        videoBG.setVideoURI(uri);

        videoBG.start();

        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mMediaPlayer.setLooping(true);
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddData("yes", "yes", "yes");
                LogIn();
            }


        });

    }

    public void Register()
    {
        Intent intent = new Intent(this, activity_sign_up.class);
        startActivity(intent);
    }
    private void LogIn() {
        Intent intent = new Intent(this, activity_LogIn.class);
        startActivity(intent);
    }

//    public void AddData(String username, String email, String password){
//        boolean insertData = mDatabasehelper.addData(username, email, password);
//        if (insertData)
//        {
//            toastMessage("Data Successfully Inserted");
//        }
//        else
//        {
//            toastMessage("Something went Wrong");
//        }
//    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Capture the current video position and pause the video.
        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart the video when resuming the Activity
        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}

