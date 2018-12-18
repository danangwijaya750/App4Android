package com.dngwjy.app4.presenters;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.repository.RestClient;
import com.dngwjy.app4.views.EventView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public void getDataVolley() {
        view.ShowLoading();
        StringRequest request = new StringRequest(Request.Method.GET, "https://api.myjson.com/bins/175n1u",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        view.FinishLoading();
                        Type collectionType = new TypeToken<Collection<EventModel>>() {
                        }.getType();
                        Collection<EventModel> data = gson.fromJson(response, collectionType);
                        Log.d("dataisthis", "datais" + data.size());
                        List list;
                        if (data instanceof List)
                            list = (List) data;
                        else
                            list = new ArrayList(data);
                        view.LoadData(list);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("erroris", error.getLocalizedMessage());
                    }
                });
        Volley.newRequestQueue(context).add(request);
    }
}
