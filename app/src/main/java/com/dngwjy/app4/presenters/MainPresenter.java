package com.dngwjy.app4.presenters;

import android.util.Log;
import android.widget.Toast;

import com.dngwjy.app4.data.models.MasjidModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getData(){
        view.showLoad();
    Call<List<MasjidModel>> listCall= RestClient.restRepo().getMasjid();
    listCall.enqueue(new Callback<List<MasjidModel>>() {
        @Override
        public void onResponse(Call<List<MasjidModel>> call, Response<List<MasjidModel>> response) {
            view.finishLoad();
            view.showData(response.body());
        }

        @Override
        public void onFailure(Call<List<MasjidModel>> call, Throwable t) {
            Log.d("error ","error get Data "+t.getLocalizedMessage());
        }
    });
}
}
