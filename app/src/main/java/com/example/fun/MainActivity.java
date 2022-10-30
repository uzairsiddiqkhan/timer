package com.example.fun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    CountDownTimer timer;

    ConstraintLayout layout;
     VideoView videoView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =findViewById(R.id.timer);
        videoView =(VideoView) findViewById(R.id.videoView);
        layout =findViewById(R.id.data);

        timer();




    }
    public void timer(){
        timer =new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                String duration = String.format(Locale.ENGLISH,"%02d : %02d"
                , TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))
                                         );
                tv.setText(duration);


            }

            @Override
            public void onFinish() {
                layout.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                video();

            }
        }.start();
    }

    public  void video(){

        String videopath ="android.resource://"+getPackageName()+"/"+ R.raw.horror;
        Uri uri =Uri.parse(videopath);
        videoView.setVideoURI(uri);

        // for Media controlling
//        MediaController mediaController =new MediaController(MainActivity.this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

        videoView.start();
    }
}