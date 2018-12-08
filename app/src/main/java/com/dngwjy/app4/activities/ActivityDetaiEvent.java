package com.dngwjy.app4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.EventModel;

public class ActivityDetaiEvent extends AppCompatActivity {
TextView textView1,textView2,textView3;
ImageView imageView;
EventModel data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        initData();
        getSupportActionBar().isHideOnContentScrollEnabled();
        imageView =findViewById(R.id.ImageView);
        textView1=findViewById(R.id.eventName);
        textView2=findViewById(R.id.eventDate);
        textView3=findViewById(R.id.eventLocation);
        Glide.with(this).load(data.getFeatureImage()).into(imageView);
        textView1.setText(data.getTitle());
        textView2.setText("Tanggal Event    :"+data.getEventDate());
        textView3.setText("Deskripsi Event  :"+data.getBody());
    }
    void  initData(){
        data= getIntent().getExtras().getParcelable("data");
        Log.d("init", "initData: "+data.getTitle());
    }
}
