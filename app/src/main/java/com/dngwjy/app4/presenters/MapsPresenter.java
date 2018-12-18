package com.dngwjy.app4.presenters;

import android.content.Context;
import android.util.Log;

import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.MapsView;

import java.util.List;

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

}
