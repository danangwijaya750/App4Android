package com.dngwjy.app4.views;

import android.content.Context;

import com.dngwjy.app4.data.models.EventModel;

import java.util.List;

public interface MasjidEventView {
    void ShowLoading();
    void HideLoading();
    void ShowData(List<EventModel> data);
}
