package com.kids.counting.math.games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Locale;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyView> {

    private String[] listdata;
    Context context;
    TextToSpeech tts;


    public MyListAdapter(String[] listdata, Context context) {
        this.listdata = listdata;
        this.context=context;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.counting, parent, false);
        MyView viewHolder = new MyView(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.MyView holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(listdata[position]);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation(holder.itemView,position);
                tts=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.US);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                            }
                            else{
                                tts.speak(String.valueOf(holder.textView.getText()), TextToSpeech.QUEUE_FLUSH, null);
                                tts.setSpeechRate(0.1f);
                                tts.setPitch(0.01f);
                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }
    public static class MyView extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyView(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.digitstext);
        }
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
            viewToAnimate.startAnimation(animation);
    }
}
