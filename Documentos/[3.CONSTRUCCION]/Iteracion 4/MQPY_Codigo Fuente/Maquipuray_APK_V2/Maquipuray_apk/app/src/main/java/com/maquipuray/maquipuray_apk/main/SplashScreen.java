package com.maquipuray.maquipuray_apk.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.maquipuray.maquipuray_apk.R;

public class SplashScreen extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);


        videoView = findViewById(R.id.videoView);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movil);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                jumpLogin();
            }
        });
        videoView.start();

//        try {
//            VideoView videoHolder = new VideoView(this);

//            setContentView(videoHolder);
//            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movil);
//            videoHolder.setVideoURI(video);
//
//            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                public void onCompletion(MediaPlayer mp) {
//                    jumpLogin();
//                }
//            });
//            videoHolder.start();
//        } catch (Exception ex) {
//            jumpLogin();
//        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        jumpLogin();
//        return true;
//    }

    private void jumpLogin() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
