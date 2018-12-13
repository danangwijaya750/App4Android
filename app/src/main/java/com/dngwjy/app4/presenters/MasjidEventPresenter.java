package com.dngwjy.app4.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.MapsView;
import com.dngwjy.app4.views.MasjidEventView;

import java.util.List;

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

    public void getData(String id){
        view.ShowLoading();
        Call<List<EventModel>> listCall= RestClient.restRepo().eventOfMasjid();
        listCall.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                view.HideLoading();
                Log.d("response Length","length "+response.body().size());
                Toast.makeText(context.getApplicationContext(),"data"+response.body().toString(),Toast.LENGTH_SHORT).show();
                view.ShowData(response.body());
            }
            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                view.HideLoading();
                Toast.makeText(context.getApplicationContext(),"error "+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
