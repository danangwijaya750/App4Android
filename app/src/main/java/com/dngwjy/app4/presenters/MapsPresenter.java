package com.dngwjy.app4.presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.MapsView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsPresenter {
    MapsView view;
    Context context;

    public MapsPresenter(MapsView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getData() {
        view.shoLoad();
        Call<List<MasjidModel>> listCall = RestClient.restRepo().getMasjid();
        listCall.enqueue(new Callback<List<MasjidModel>>() {
            @Override
            public void onResponse(Call<List<MasjidModel>> call, Response<List<MasjidModel>> response) {
                view.finishLoadin();
                view.showData(response.body());
                Log.d("data", "onResponse: " + response.body().size());
            }

            @Override
            public void onFailure(Call<List<MasjidModel>> call, Throwable t) {
                view.finishLoadin();
                Log.d("error", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
    @SuppressLint("CheckResult")
    public  void getDataByObserve(){
        view.LoadingData();
        getObserveable().subscribeWith(getObserver());
    }
    private Observable<List<MasjidModel>> getObserveable(){
        return new RestClient().restRepo().getMasjidObserve().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @SuppressLint("CheckResult")
    public void getMasjidTerdekat(double lat, double longit){
        view.LoadingData();
        getTerdekatObservable(lat,longit).subscribeWith(getObserver());
    }

    private Observable<List<MasjidModel>> getTerdekatObservable(double lat,double longit){
        return  new RestClient().restRepo().masjidTerdekat(lat,longit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    public DisposableObserver<List<MasjidModel>> getObserver(){
        return new DisposableObserver<List<MasjidModel>>() {
            @Override
            public void onNext(List<MasjidModel> masjidModels) {
                Log.d("MasjidFrag", "onNext: "+masjidModels.size());
                view.showData(masjidModels);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MasjidFrag", "onError: "+e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.d("MasjidFrag", "onComplete: Completed");
                view.finishLoadin();
            }
        };
    }

}
