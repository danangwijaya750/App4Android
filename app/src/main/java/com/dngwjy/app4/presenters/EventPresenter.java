package com.dngwjy.app4.presenters;

import android.util.Log;

import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.EventView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPresenter {
EventView view;

    public EventPresenter(EventView view) {
        this.view = view;
    }

    public void getData(){
        view.ShowLoading();
        Call<List<EventModel>> listCall= RestClient.restRepo().getEvent();
        listCall.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                view.LoadData(response.body());
                view.FinishLoading();
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                Log.d("error getData", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
}
