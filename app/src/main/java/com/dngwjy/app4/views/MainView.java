package com.dngwjy.app4.views;

import com.dngwjy.app4.data.models.MasjidModel;

import java.util.List;

public interface MainView {
    void showLoad();

    void finishLoad();

    void showData(List<MasjidModel> data);
}
