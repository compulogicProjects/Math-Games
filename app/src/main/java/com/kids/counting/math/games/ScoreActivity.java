package com.kids.counting.math.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kids.counting.math.games.ads.Ad_Helper;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    TextView scoretitle,score1;
    TextView btnhome,btnnext;
    Toolbar toolbar;
    RelativeLayout relativeLayout;
    ArrayList<QuizModel> QuizModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoretitle=findViewById(R.id.scoretitle);
        score1=findViewById(R.id.score);

            btnhome =findViewById(R.id.btnrestart);
            btnnext=findViewById(R.id.btnnext);
            toolbar=findViewById(R.id.toolbaradd);
            toolbar.setTitle("Score");

        relativeLayout=findViewById(R.id.mainLayout);

        Ad_Helper.showNativeAd(this,relativeLayout);


            Intent intent= getIntent();
            int score = intent.getIntExtra("score",0);
            int score2 = intent.getIntExtra("score2",0);
            int activity=intent.getIntExtra("Activity",0);

            scoretitle.setText(score+"/10");
            score1.setText("Your Score is");


            if (activity==5) {
                if (score > score2) {
                    score1.setText("Player 1 Won !");
                    scoretitle.setText("Score :" + score + "/10");
                } else if (score2>score){
                    score1.setText("player 2 Won !");
                    scoretitle.setText("Score:" + score2 + "/10");
                }
                else{
                    score1.setText("Oops!");
                    scoretitle.setText("Draw");
                }
            }



            btnhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(ScoreActivity.this, secondActivity.class);
                        startActivity(intent);
                        finish();
                }
            });


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.intentconstant=4;
                Ad_Helper.showIntersitial(ScoreActivity.this);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, secondActivity.class));
        finish();
    }
}