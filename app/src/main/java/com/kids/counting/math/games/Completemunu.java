package com.kids.counting.math.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kids.counting.math.games.ads.Ad_Helper;


public class Completemunu extends AppCompatActivity {
    TextView btnadd,btnmul,btndiv,btnsub;
    ImageView backimage;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completemunu);
        btnadd=findViewById(R.id.btnadd);
        btnsub=findViewById(R.id.btnsub);
        btnmul=findViewById(R.id.btnmul);
        btndiv=findViewById(R.id.btndivide);
        backimage=findViewById(R.id.backimage);

        relativeLayout=findViewById(R.id.mainLayout);

        Ad_Helper.showNativeAd(this,relativeLayout);

        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(Completemunu.this,Competition.class);
                intent.putExtra("complete",1);
                startActivity(intent);
                finish();
            }
        });
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(Completemunu.this,Competition.class);
                intent.putExtra("complete",2);
                startActivity(intent);
                finish();
            }
        });
        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(Completemunu.this,Competition.class);
                intent.putExtra("complete",3);
                startActivity(intent);
                finish();
            }
        });
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(Completemunu.this,Competition.class);
                intent.putExtra("complete",4);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent= new Intent(Completemunu.this,secondActivity.class);
        startActivity(intent);
        finish();
    }
}