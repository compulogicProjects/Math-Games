package com.kids.counting.math.games;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kids.counting.math.games.ads.Ad_Helper;

import java.util.zip.Inflater;

public class secondActivity extends AppCompatActivity {
    TextView btnadd, btnmul, btndiv, btnsub, btncomp, btncount;
    Toolbar toolbar;
    RelativeLayout relativeLayout;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btnmul = findViewById(R.id.btnmul);
        btndiv = findViewById(R.id.btndivide);
        btncomp = findViewById(R.id.comp);
        btncount = findViewById(R.id.btncount);
        toolbar = findViewById(R.id.toolbaradd);
        toolbar.setTitle("Math Game");

        relativeLayout = findViewById(R.id.mainLayout);

        Ad_Helper.showNativeAd(this, relativeLayout);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.intentconstant = 1;
                Ad_Helper.showIntersitial(secondActivity.this);

            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondActivity.this, Subtraction.class);
                startActivity(intent);
            }
        });

        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondActivity.this, Multiplication.class);
                startActivity(intent);
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondActivity.this, Division.class);
                startActivity(intent);
            }
        });
        btncomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.intentconstant = 2;
                Ad_Helper.showIntersitial(secondActivity.this);
            }
        });
        btncount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.intentconstant = 3;
                Ad_Helper.showIntersitial(secondActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {


        /*dialog = new Dialog(this);
        dialog.setContentView(R.layout.backscreen);
        dialog.show();*/
        ViewGroup viewGroup= findViewById(android.R.id.content);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.backscreen,viewGroup,false);
        builder.setCancelable(false);
        builder.setView(view);

        Button btncancel= view.findViewById(R.id.btncancel);
        Button btnexit= view.findViewById(R.id.btnexit);
        RatingBar ratingBar= view.findViewById(R.id.ratingbar);
        AlertDialog alertDialog= builder.create();

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finishAffinity();
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v==5){
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.kids.counting.math.games"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                else{
                    View view = LayoutInflater.from(secondActivity.this).inflate(R.layout.exitscreen,viewGroup,false);
                    builder.setCancelable(false);
                    builder.setView(view);

                    Button btncancel=view.findViewById(R.id.btncancel);
                    Button btnsubmit=view.findViewById(R.id.btnsubmit);
                    TextView feedbacktext= view.findViewById(R.id.feedbacktext);

                    AlertDialog alertDialog1= builder.create();

                    btncancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog1.dismiss();
                        }
                    });
                    btnsubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (feedbacktext.getText().toString().isEmpty()) {
                                Toast.makeText(secondActivity.this, "Please Enter Feedback!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(secondActivity.this, "Thanks For Your Feedback!", Toast.LENGTH_SHORT).show();
                                alertDialog1.dismiss();
                                alertDialog.dismiss();
                            }
                        }
                    });
                    alertDialog1.show();
                }
            }
        });

        alertDialog.show();

        }


}