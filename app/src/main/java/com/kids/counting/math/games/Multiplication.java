package com.kids.counting.math.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.kids.counting.math.games.ads.Facebook_AdHelper;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Multiplication extends AppCompatActivity {
    TextView num1,num2,attemptedquestion,score,scoretitle,answer;
    TextView option1,option2,option3,option4;
    ArrayList<QuizModel> QuizModelArrayList;
    int questionAttempted=0,currentpos,currentScore=0;
    Random random;
    ImageView checkans,back,music;
    CardView cd;
    MediaPlayer wrong,right;
    boolean musicon=true;
    SharedPreferences sharedPreferences;
    public  static final String prefname="mypref";
    public static final String musc="getmusic";
    int i=1;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
        attemptedquestion=findViewById(R.id.attemptedquestion);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        score=findViewById(R.id.score);
        scoretitle=findViewById(R.id.scoretitle);
        answer=findViewById(R.id.answer);
        checkans=findViewById(R.id.checkans);
        back=findViewById(R.id.backimage);
        cd=findViewById(R.id.card1);
        wrong=MediaPlayer.create(Multiplication.this,R.raw.wrong);
        right=MediaPlayer.create(Multiplication.this,R.raw.right);
        music=findViewById(R.id.music);
        QuizModelArrayList= new ArrayList<>();
        random=new Random();
        getQuizQuestion(QuizModelArrayList);

        //linearLayout=findViewById(R.id.layad);

        //Ad_Helper.loadMediationBannerAd(this,linearLayout);
        Facebook_AdHelper.loadFacebookBannerAd(this);

        currentpos=random.nextInt(QuizModelArrayList.size());
        setDataToView(currentpos);
        sharedPreferences=getSharedPreferences("prefname",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit() ;
        sharedPreferences sp= new sharedPreferences();
        int name =  sp.getValue(this,"musc");
        if (name==1){
            music.setImageResource(R.drawable.ic_baseline_volume_up_24);
            musicon=false;
            i=1;
        }
        if (name==2){
            i=2;
            musicon=true;
            music.setImageResource(R.drawable.ic_baseline_volume_off_24);
        }
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicon == true) {
                    Toast.makeText(Multiplication.this, "Sound On", Toast.LENGTH_SHORT).show();
                    music.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    i=1;
                    sp.saveInt(Multiplication.this,"musc",i);
                    musicon=false;
                }else {
                    Toast.makeText(Multiplication.this,"Sound Off",Toast.LENGTH_SHORT).show();
                    music.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    i=2;
                    sp.saveInt(Multiplication.this,"musc",i);
                    musicon=true;
                }
            }
        });
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option1.setBackgroundColor(option1.getContext().getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:\n"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else {
                    option1.setBackgroundColor(option1.getContext().getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                CharSequence opt1= option1.getText();
                answer.setText(opt1);
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option1.setBackgroundColor(option1.getContext().getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Multiplication.this,R.anim.sample_anim);
                        cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToView(currentpos);
                    }
                },2000);

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option2.setBackgroundColor(option2.getContext().getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    option2.setBackgroundColor(option2.getContext().getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                CharSequence opt1= option2.getText();
                answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option2.setBackgroundColor(option2.getContext().getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Multiplication.this,R.anim.sample_anim);
                        cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToView(currentpos);
                    }
                },2000);

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option3.setBackgroundColor(option3.getContext().getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    option3.setBackgroundColor(option3.getContext().getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                CharSequence opt1= option3.getText();
                answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option3.setBackgroundColor(option3.getContext().getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Multiplication.this,R.anim.sample_anim);
                        cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToView(currentpos);
                    }
                },2000);

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option4.setBackgroundColor(option4.getContext().getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    option4.setBackgroundColor(option4.getContext().getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                CharSequence opt1= option4.getText();
                answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option4.setBackgroundColor(option4.getContext().getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Multiplication.this,R.anim.sample_anim);
                        cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToView(currentpos);
                    }
                },2000);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Multiplication.this, secondActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @SuppressLint("SetTextI18n")
    private void setDataToView(int currentpos) {
        if (questionAttempted==10){
            Intent intent=new Intent(this, ScoreActivity.class);
            intent.putExtra("score",currentScore);
            intent.putExtra("Activity",4);
            startActivity(intent);
        }
        else {
            attemptedquestion.setText("Question Attempted :"+questionAttempted+"/10");
            num1.setText(QuizModelArrayList.get(currentpos).getNum1());
            num2.setText(QuizModelArrayList.get(currentpos).getNum2());
            option1.setText(QuizModelArrayList.get(currentpos).getOption1());
            option2.setText(QuizModelArrayList.get(currentpos).getOption2());
            option3.setText(QuizModelArrayList.get(currentpos).getOption3());
            option4.setText(QuizModelArrayList.get(currentpos).getOption4());
        }
    }
    private void getQuizQuestion(ArrayList<QuizModel> QuizModelArrayList) {
        QuizModelArrayList.add(new QuizModel("6","6","36","10","24","26","36"));
        QuizModelArrayList.add(new QuizModel("7","9","72","86","35","63","63"));
        QuizModelArrayList.add(new QuizModel("5","4","20","25","30","40","20"));
        QuizModelArrayList.add(new QuizModel("9","7","36","45","54","63","63"));
        QuizModelArrayList.add(new QuizModel("8","5","30","40","45","50","40"));
        QuizModelArrayList.add(new QuizModel("4","9","36","38","40","42","36"));
        QuizModelArrayList.add(new QuizModel("9","8","54","63","72","81","72"));
        QuizModelArrayList.add(new QuizModel("7","8","49","56","63","70","56"));
        QuizModelArrayList.add(new QuizModel("6","6","18","24","30","36","36"));
        QuizModelArrayList.add(new QuizModel("4","6","28","24","30","36","24"));
        QuizModelArrayList.add(new QuizModel("4","3","12","17","15","6","12"));
        QuizModelArrayList.add(new QuizModel("7","6","38","27","42","35","42"));

    }
}