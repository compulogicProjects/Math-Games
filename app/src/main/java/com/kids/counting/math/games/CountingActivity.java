package com.kids.counting.math.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kids.counting.math.games.ads.Facebook_AdHelper;


public class CountingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyListAdapter myListAdapter;
    ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        recyclerView=findViewById(R.id.recyclerview);
        backimage=findViewById(R.id.backimage);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        String[] listdata = new String[] {"1", "2","3", "4","5","6","7","8","9","10","11", "12","13", "14","15","16","17","18","19","20","21", "22","23", "24","25","26","27","28","29","30","41", "42","43", "44","45","46","47","48","49","50","51", "52","53", "54","55","56","57","58","59","60","61", "62","63", "64","65","66","67","68","69","70","71", "72","73", "74","75","76","77","78","79","80","81", "82","83", "84","85","86","87","88","89","90","91", "92","93", "94","95","96","97","98","99","100"};
        myListAdapter=new MyListAdapter(listdata,this);
        recyclerView.setAdapter(myListAdapter);

     //   LinearLayout linearLayout= findViewById(R.id.layad);
       // Ad_Helper.loadMediationBannerAd(this,linearLayout);
        Facebook_AdHelper.loadFacebookBannerAd(this);

        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CountingActivity.this,secondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }