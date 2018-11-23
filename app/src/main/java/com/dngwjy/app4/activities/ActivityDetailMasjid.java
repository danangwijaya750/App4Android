package com.dngwjy.app4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dngwjy.app4.R;
import com.dngwjy.app4.data.models.MasjidModel;

public class ActivityDetailMasjid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_masjid);
        initData();
    }
    void initData(){
        MasjidModel model = getIntent().getExtras().getParcelable("data");
        Log.d("init", "initData: "+model.getNama_masjid());

    }
}
