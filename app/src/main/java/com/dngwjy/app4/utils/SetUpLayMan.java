package com.dngwjy.app4.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SetUpLayMan {

    public static LinearLayoutManager linearLayoutManager(Context context){
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return  layoutManager;
    }


}
