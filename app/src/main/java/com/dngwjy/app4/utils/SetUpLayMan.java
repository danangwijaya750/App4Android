package com.dngwjy.app4.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

public class SetUpLayMan {

    public static LinearLayoutManager linearLayoutManager(Context context) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    public static GridLayoutManager gridLayoutManager(Context context, int col) {
        return new GridLayoutManager(context, col);
    }


}
