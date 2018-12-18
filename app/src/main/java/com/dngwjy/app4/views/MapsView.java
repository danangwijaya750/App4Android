package com.dngwjy.app4.views;

import com.dngwjy.app4.data.models.MasjidModel;

import java.util.List;

public interface MapsView {
    void LoadingData();

    void shoLoad();

    void finishLoadin();

    void showData(List<MasjidModel> data);
}
