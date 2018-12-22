package com.dngwjy.app4.presenters;

import android.content.Context;
import android.util.Log;

import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.EventView;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPresenter {
    EventView view;
    Context context;
    Gson gson;

    public EventPresenter(EventView view, Context context, Gson gson) {
        this.view = view;
        this.context = context;
        this.gson = gson;
    }

    public void getData() {
        view.ShowLoading();
        Call<List<EventModel>> listCall = RestClient.restRepo().getEvent();
        listCall.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                Log.d("res", "onResponse: " + response.body());
                view.LoadData(response.body());
                view.FinishLoading();
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                Log.d("error getData", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void getDataByObserve() {
        view.ShowLoading();
        getObserveable().subscribeWith(getObserver());
    }

    public Observable<List<EventModel>> getObserveable() {
        return new RestClient().restRepo().getEventObserve().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<List<EventModel>> getObserver() {
        return new DisposableObserver<List<EventModel>>() {
            @Override
            public void onNext(List<EventModel> eventModels) {
                Log.d("EventFrag", "onNext: ");
                view.LoadData(eventModels);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("EventFrag", "onError: " + e.getLocalizedMessage());
                view.FinishLoading();
            }

            @Override
            public void onComplete() {
                Log.d("EventFrag", "onComplete: completed");
                view.FinishLoading();
            }
        };
    }


}
