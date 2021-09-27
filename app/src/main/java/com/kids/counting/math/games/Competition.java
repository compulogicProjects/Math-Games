package com.kids.counting.math.games;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Competition extends AppCompatActivity {

    ProgressBar pg;
    int currentprogress;
    TextView time;
    ImageView backimage,music;

    TextView num1,num2,attemptedquestion,score,scoretitle,answer,symbol,player2symbol;
    TextView option1,option2,option3,option4;
    TextView player2num1,player2num2,player2attemptedquestion,player2score,player2answer;
    TextView player2option1,player2option2,player2option3,player2option4;

    ArrayList<QuizModel> QuizModelArrayList= new ArrayList<QuizModel>();

    int questionAttempted=0;
   int  player2currentScore=0;
    int player2questionAttempted=0;
    int currentpos,currentScore=0;
    Random random;
    sharedPreferences sp;
    ImageView checkans,player2checkans;
    CardView cd,player2cd;
    MediaPlayer wrong,right;
    boolean musicon=false;
    SharedPreferences sharedPreferences;

    int i=1;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iniWidgets();

        wrong=MediaPlayer.create(Competition.this,R.raw.wrong);
        right=MediaPlayer.create(Competition.this,R.raw.right);

        random=new Random();

        getQuizQuestion(QuizModelArrayList);


        currentpos=random.nextInt(QuizModelArrayList.size());


        setDataToViewPlayer1(currentpos);
        setDataToViewPlayer2(currentpos);

        getSharePref();


        cdt=new CountDownTimer(31000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
            time.setText(currentprogress+"/30");
            pg.setMax(30);
            pg.setProgress(currentprogress);
            currentprogress++;
            }
            @Override
            public void onFinish() {
                Animation animation= AnimationUtils.loadAnimation(Competition.this,
                        R.anim.sample_anim);
                cd.startAnimation(animation);
                player2cd.startAnimation(animation);
                currentpos=random.nextInt(QuizModelArrayList.size());
                setDataToViewPlayer1(currentpos);
                setDataToViewPlayer2(currentpos);
                currentprogress=0;
                this.start();
            }
        }.start();


        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicon == true) {
                    Toast.makeText(Competition.this, "Sound On", Toast.LENGTH_SHORT).show();
                    music.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    i=1;
                    sp.saveInt(Competition.this,"musc",i);
                    musicon=false;
                }
                else {
                    i=2;
                        music.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    sp.saveInt(Competition.this,"musc",i);
                      musicon=true;
                }
            }
        });



        //player 1 option 1
        option1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().
                        trim().toLowerCase().equals(option1.getText()
                        .toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option1.setBackgroundColor(getResources().
                            getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(
                            R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else {
                    option1.setBackgroundColor(getResources().getColor(R.color.red));
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
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                currentprogress=0;
                cdt.cancel();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(
                                Competition.this,R.anim.sample_anim);
                        cd.startAnimation(animation);
                        player2cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer1(currentpos);
                        setDataToViewPlayer2(currentpos);
                        if (cdt != null){
                            cdt.start();
                        }
                    }
                },2000);

            }
        });



        //player 2 option 1
        player2option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    player2currentScore++;
                    player2option1.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(player2currentScore);
                    player2score.setText("Score:"+cS);
                    player2checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else {
                    player2option1.setBackgroundColor(getResources().getColor(R.color.red));
                    player2checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                CharSequence opt1= player2option1.getText();
                player2answer.setText(opt1);
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                currentprogress=0;
              cdt.cancel();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        player2option1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Competition.this,R.anim.sample_anim);
                        player2cd.startAnimation(animation);
                        cd.startAnimation(animation);
                        player2answer.setText("");
                        player2checkans.setImageDrawable(null);
                        player2questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer2(currentpos);
                        setDataToViewPlayer1(currentpos);
                        if (cdt != null){
                            cdt.start();
                        }

                    }
                },2000);

            }
        });



        //player 1 option 2
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim()
                        .toLowerCase().equals(option2.getText().toString().trim()
                                .toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option2.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    option2.setBackgroundColor(getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                CharSequence opt1= option2.getText();
                answer.setText(opt1);
                currentprogress=0;
               cdt.cancel();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (cdt != null){
                            cdt.start();
                        }
                        // timer
                        option2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);

                        Animation animation= AnimationUtils.loadAnimation(Competition.this,
                                R.anim.sample_anim);
                        player2cd.startAnimation(animation);
                        cd.startAnimation(animation);

                        answer.setText("");
                        checkans.setImageDrawable(null);

                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());

                        setDataToViewPlayer1(currentpos);
                        setDataToViewPlayer2(currentpos);
                    }
                },2000);

            }
        });



        //Player 2 Option 2
        player2option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    player2currentScore++;
                    player2option2.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(player2currentScore);
                    player2score.setText("Score:"+cS);
                    player2checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    player2option2.setBackgroundColor(getResources().getColor(R.color.red));
                    player2checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                currentprogress=0;
                cdt.cancel();

                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                CharSequence opt1= player2option2.getText();
                player2answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        player2option2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Competition.this,R.anim.sample_anim);
                        player2cd.startAnimation(animation);
                        cd.startAnimation(animation);
                        player2answer.setText("");
                        player2checkans.setImageDrawable(null);
                        player2questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer2(currentpos);
                        setDataToViewPlayer1(currentpos);
                        if (cdt != null){
                            cdt.start();
                        }

                    }
                },2000);

            }
        });



        //Option 3 Player 1
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option3.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    option3.setBackgroundColor(getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                currentprogress=0;
               cdt.cancel();

                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                CharSequence opt1= option3.getText();
                answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cdt != null){
                            cdt.start();
                        }

                        option3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Competition.this,R.anim.sample_anim);
                        player2cd.startAnimation(animation);
                        cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer2(currentpos);
                        setDataToViewPlayer1(currentpos);
                    }
                },2000);

            }
        });



        //Option 3 Player 2
        player2option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    player2currentScore++;
                    player2option3.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(player2currentScore);
                    player2score.setText("Score:"+cS);
                    player2checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    player2option3.setBackgroundColor(getResources().getColor(R.color.red));
                    player2checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                currentprogress=0;
             cdt.cancel();
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                CharSequence opt1= player2option3.getText();
                player2answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cdt != null){
                            cdt.start();
                        }

                        player2option3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Competition.this,R.anim.sample_anim);
                        player2cd.startAnimation(animation);
                        cd.startAnimation(animation);
                        player2answer.setText("");
                        player2checkans.setImageDrawable(null);
                        player2questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer1(currentpos);
                        setDataToViewPlayer2(currentpos);
                    }
                },2000);

            }
        });



        //option4 player1
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    currentScore++;
                    option4.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(currentScore);
                    score.setText("Score:"+cS);
                    checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    option4.setBackgroundColor(getResources().getColor(R.color.red));
                    checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                currentprogress=0;
               cdt.cancel();

                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                CharSequence opt1= option4.getText();
                answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cdt != null){
                            cdt.start();
                        }

                        option4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Competition.this,R.anim.sample_anim);
                        cd.startAnimation(animation);
                        player2cd.startAnimation(animation);
                        answer.setText("");
                        checkans.setImageDrawable(null);
                        questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer1(currentpos);
                        setDataToViewPlayer2(currentpos);
                    }
                },2000);

            }
        });



        // option 4 player2
        player2option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizModelArrayList.get(currentpos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase(Locale.ROOT))){
                    player2currentScore++;
                    player2option4.setBackgroundColor(getResources().getColor(R.color.Green));
                    String cS= String.valueOf(player2currentScore);
                    player2score.setText("Score:"+cS);
                    player2checkans.setImageResource(R.drawable.ic_baseline_check);
                    if (i == 1) {
                        right.start();
                    }
                }
                else{
                    player2option4.setBackgroundColor(getResources().getColor(R.color.red));
                    player2checkans.setImageResource(R.drawable.ic_baseline_clear);
                    if (i == 1) {
                        wrong.start();
                    }
                }
                currentprogress=0;
               cdt.cancel();

                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                player2option1.setClickable(false);
                player2option2.setClickable(false);
                player2option3.setClickable(false);
                player2option4.setClickable(false);
                CharSequence opt1= player2option4.getText();
                player2answer.setText(opt1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cdt != null){
                            cdt.start();
                        }

                        player2option4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option1.setClickable(true);
                        option2.setClickable(true);
                        option3.setClickable(true);
                        option4.setClickable(true);
                        player2option1.setClickable(true);
                        player2option2.setClickable(true);
                        player2option3.setClickable(true);
                        player2option4.setClickable(true);
                        Animation animation= AnimationUtils.loadAnimation(Competition.this,R.anim.sample_anim);
                        player2cd.startAnimation(animation);
                        cd.startAnimation(animation);
                        player2answer.setText("");
                        player2checkans.setImageDrawable(null);
                        player2questionAttempted++;
                        currentpos=random.nextInt(QuizModelArrayList.size());
                        setDataToViewPlayer2(currentpos);
                        setDataToViewPlayer1(currentpos);
                    }
                },2000);

            }
        });



       //back button code
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Competition.this, Completemunu.class));
               finish();
            }
        });
    }


    private void getSharePref(){

        sharedPreferences=getSharedPreferences("prefname"
                ,MODE_PRIVATE);
        sp= new sharedPreferences();
        int name =  sp.getValue(this,"musc");
        if (name==1){
            music.setImageResource(R.drawable.ic_baseline_volume_up_24);
            musicon=false;
            i=1;
        }
        if (name==2){
            music.setImageResource(R.drawable.ic_baseline_volume_off_24);
            musicon=true;
            i=2;
        }

    }


    //Set Data Method
    @SuppressLint("SetTextI18n")
    private void setDataToViewPlayer1(int currentpos) {
        if (questionAttempted==10||player2questionAttempted==10){
            Intent intent=new Intent(this,
                    ScoreActivity.class);
            intent.putExtra("score",currentScore);
            intent.putExtra("score2",player2currentScore);
            intent.putExtra("Activity",5);
            Intent intent1 = getIntent();
            int complete = intent1.getIntExtra("complete",0);
            if (complete==1){
                intent.putExtra("complete",1);
            }
            if (complete==2){
                intent.putExtra("complete",2);
            }
            if (complete==3){
                intent.putExtra("complete",3);
            }
            if (complete==4){
                intent.putExtra("complete",4);
            }
            if (cdt!=null){
                cdt.cancel();
                cdt = null;
            }
            startActivity(intent);

        }
        else {

            Intent intent = getIntent();
            int complete = intent.getIntExtra("complete",
                    0);
            int compete=intent.getIntExtra("compete",0);
            if (complete==1||compete==1){
                symbol.setText("+");
                player2symbol.setText("+");
            }
            if (complete==2||compete==2){
                symbol.setText("-");
                player2symbol.setText("-");
            }
            if (complete==3||compete==3){
                symbol.setText("x");
                player2symbol.setText("x");
            }
            if (complete==4||compete==4){
                symbol.setText("÷");
                player2symbol.setText("÷");
            }
            attemptedquestion.setText("Question Attempted :"
                    +questionAttempted+"/10");
            num1.setText(QuizModelArrayList.get(currentpos).
                    getNum1());
            num2.setText(QuizModelArrayList.get(currentpos).
                    getNum2());
            option1.setText(QuizModelArrayList.
                    get(currentpos).getOption1());
            option2.setText(QuizModelArrayList.
                    get(currentpos).getOption2());
            option3.setText(QuizModelArrayList.
                    get(currentpos).getOption3());
            option4.setText(QuizModelArrayList.
                    get(currentpos).getOption4());
        }
    }

    //........set data method for player two
    private void setDataToViewPlayer2(int currentpos) {
        if (player2questionAttempted==10||questionAttempted==10){
            Intent intent=new Intent(this,
                    ScoreActivity.class);
            intent.putExtra("score2",player2currentScore);
            intent.putExtra("score",currentScore);
            intent.putExtra("Activity",5);
            Intent intent1 = getIntent();
            int complete = intent1.getIntExtra("complete",0);
            if (complete==1){
               intent.putExtra("complete",1);
            }
            if (complete==2){
                intent.putExtra("complete",2);
            }
            if (complete==3){
                intent.putExtra("complete",3);
            }
            if (complete==4){
                intent.putExtra("complete",4);
            }
            if (cdt!=null){
                cdt.cancel();
                cdt = null;
            }
            startActivity(intent);


        }
        else {
            Intent intent = getIntent();
            int complete = intent.getIntExtra("complete",0);
            int compete= intent.getIntExtra("compete" ,0);
            if (complete==1||compete==1){
                symbol.setText("+");
                player2symbol.setText("+");
            }
            if (complete==2||compete==2){
                symbol.setText("-");
                player2symbol.setText("-");
            }
            if (complete==3||compete==3){
                symbol.setText("x");
                player2symbol.setText("x");
            }
            if (complete==4||compete==4){
                symbol.setText("÷");
                player2symbol.setText("÷");
            }
            player2attemptedquestion.setText("Question Attempted :"
                    +player2questionAttempted+"/10");
            player2num1.setText(QuizModelArrayList.get(currentpos)
                    .getNum1());
            player2num2.setText(QuizModelArrayList.get(currentpos).
                    getNum2());
            player2option1.setText(QuizModelArrayList.get(currentpos).
                    getOption1());
            player2option2.setText(QuizModelArrayList.get(currentpos).
                    getOption2());
            player2option3.setText(QuizModelArrayList.get(currentpos).
                    getOption3());
            player2option4.setText(QuizModelArrayList.get(currentpos).
                    getOption4());
        }
    }


    //Question method
    private void getQuizQuestion(ArrayList<QuizModel> QuizModelArrayList) {

        Intent intent = getIntent();
        int complete = intent.getIntExtra("complete", 0);
        int compete = intent.getIntExtra("compete", 0);

        if (complete == 1||compete==1) {
            QuizModelArrayList.add(new QuizModel("7", "9", "29", "17", "16", "88", "16"));
            QuizModelArrayList.add(new QuizModel("8", "1", "7", "2", "9", "10", "9"));
            QuizModelArrayList.add(new QuizModel("4", "5", "3", "1", "9", "8", "9"));
            QuizModelArrayList.add(new QuizModel("3", "5", "6", "9", "8", "7", "8"));
            QuizModelArrayList.add(new QuizModel("4", "4", "2", "6", "7", "8", "8"));
            QuizModelArrayList.add(new QuizModel("5", "1", "6", "8", "4", "5", "6"));
            QuizModelArrayList.add(new QuizModel("3", "4", "8", "4", "6", "7", "7"));
            QuizModelArrayList.add(new QuizModel("6", "5", "11", "12", "74", "71", "11"));
            QuizModelArrayList.add(new QuizModel("5", "8", "13", "8", "9", "5", "13"));
            QuizModelArrayList.add(new QuizModel("7", "6", "28", "14", "13", "35", "13"));
            QuizModelArrayList.add(new QuizModel("8", "9", "28", "17", "15", "36", "17"));
            QuizModelArrayList.add(new QuizModel("12", "15", "38", "27", "20", "35", "27"));
        }
        if (complete==2||compete==2){
            QuizModelArrayList.add(new QuizModel("7","3","4","2","3","8","4"));
            QuizModelArrayList.add(new QuizModel("3","2","1","5","4","2","1"));
            QuizModelArrayList.add(new QuizModel("4","2","1","7","2","9","2"));
            QuizModelArrayList.add(new QuizModel("5","4","1","8","4","2","1"));
            QuizModelArrayList.add(new QuizModel("10","4","2","6","7","1","6"));
            QuizModelArrayList.add(new QuizModel("9","4","2","3","5","6","5"));
            QuizModelArrayList.add(new QuizModel("6","3","8","10","3","2","3"));
            QuizModelArrayList.add(new QuizModel("7","5","7","4","5","2","2"));
            QuizModelArrayList.add(new QuizModel("8","4","2","4","9","1","4"));
            QuizModelArrayList.add(new QuizModel("5","3","2","1","3","4","2"));
            QuizModelArrayList.add(new QuizModel("9","5","2","7","5","4","4"));
            QuizModelArrayList.add(new QuizModel("17","9","6","3","8","5","8"));
        }
        if (complete==3||compete==3){
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
        if (complete==4||compete==4){
            QuizModelArrayList.add(new QuizModel("9","3","2","7","4","3","3"));
            QuizModelArrayList.add(new QuizModel("6","1","4","6","7","3","6"));
            QuizModelArrayList.add(new QuizModel("6","2","3","4","7","2","3"));
            QuizModelArrayList.add(new QuizModel("9","1","2","9","6","10","9"));
            QuizModelArrayList.add(new QuizModel("6","3","2","6","3","4","2"));
            QuizModelArrayList.add(new QuizModel("8","2","1","8","4","3","4"));
            QuizModelArrayList.add(new QuizModel("7","1","2","4","6","7","7"));
            QuizModelArrayList.add(new QuizModel("8","4","2","8","1","9","2"));
            QuizModelArrayList.add(new QuizModel("2","2","5","0","9","1","1"));
            QuizModelArrayList.add(new QuizModel("4","2","7","2","1","4","2"));
            QuizModelArrayList.add(new QuizModel("8","1","8","1","2","4","8"));
            QuizModelArrayList.add(new QuizModel("3","1","3","7","4","5","3"));
        }
    }

    private void iniWidgets(){
        pg = findViewById(R.id.progressbar);
        time=findViewById(R.id.time);
        backimage=findViewById(R.id.backimage);
        music=findViewById(R.id.music);
        attemptedquestion=findViewById(R.id.attemptedquestion);
        music=findViewById(R.id.music);


        //player 1
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
        cd=findViewById(R.id.card1);
        player2cd=findViewById(R.id.player2card);
        symbol=findViewById(R.id.symbol);


        //player 2
        player2symbol=findViewById(R.id.player2symbol);
        player2attemptedquestion=findViewById(R.id.player2attemptedquestion);
        player2num1=findViewById(R.id.player2num1);
        player2num2=findViewById(R.id.player2num2);
        player2option1=findViewById(R.id.player2option1);
        player2option2=findViewById(R.id.player2option2);
        player2option3=findViewById(R.id.player2option3);
        player2option4=findViewById(R.id.player2option4);
        player2score=findViewById(R.id.player2score);
        player2answer=findViewById(R.id.player2answer);
        player2checkans=findViewById(R.id.player2checkans);
        player2cd=findViewById(R.id.player2card);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Competition.this,Completemunu.class);
                startActivity(intent);
                finish();
    }
}