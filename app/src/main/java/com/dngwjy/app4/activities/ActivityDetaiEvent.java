package com.dngwjy.app4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.EventModel;

public class ActivityDetaiEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        initData();
    }
    void  initData(){
        EventModel data= getIntent().getExtras().getParcelable("data");
        Log.d("init", "initData: "+data.getTitle());
    }
}
