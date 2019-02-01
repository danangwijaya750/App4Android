package com.dngwjy.app4.presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.MasjidEventView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasjidEventPresenter {
    MasjidEventView view;
    Context context;

    public MasjidEventPresenter(MasjidEventView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @SuppressLint("CheckResult")
    public void getData(String id) {
      view.ShowLoading();
        observable(id).subscribeWith(getObserver());
    }

    private Observable<List<EventModel>> observable(String id){
        return RestClient.restRepo().eventOfMasjidObserve(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    private DisposableObserver<List<EventModel>> getObserver(){
        return new DisposableObserver<List<EventModel>>() {
            @Override
            public void onNext(List<EventModel> eventModels) {
                view.ShowData(eventModels);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                view.HideLoading();
            }
        };
    }
}
