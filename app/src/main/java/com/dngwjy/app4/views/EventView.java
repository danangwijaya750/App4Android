package com.dngwjy.app4.views;

import com.dngwjy.app4.data.models.EventModel;

import java.util.List;

public interface EventView {
void ShowLoading();
void FinishLoading();
void LoadData(List<EventModel> data);
}
