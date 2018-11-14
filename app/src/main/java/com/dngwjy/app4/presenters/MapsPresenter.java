package com.dngwjy.app4.presenters;

import com.dngwjy.app4.data.models.MasjidModel;

import java.util.List;

public interface MapsPresenter {
void LoadingData();
void shoLoad();
void finishLoadin();
void showData(List<MasjidModel> data);
}
