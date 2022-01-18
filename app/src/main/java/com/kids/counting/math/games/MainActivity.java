package com.kids.counting.math.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.kids.counting.math.games.ads.Facebook_AdHelper;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
        ImageView img;
        TextView title;
        Animation animTop, animBottom;
        Timer timer;
    @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            timer = new Timer();
            img = findViewById(R.id.imglogo);
            title = findViewById(R.id.title);

            Facebook_AdHelper.iniSDK(MainActivity.this);

           /* Ad_Helper.loadIntersitialAd(this);
            Ad_Helper.loadNativeAd(this);*/


            animTop = AnimationUtils.loadAnimation(this, R.anim.top_animation);
            animBottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

            img.setAnimation(animTop);
            title.setAnimation(animBottom);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,secondActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);

        }
    }
