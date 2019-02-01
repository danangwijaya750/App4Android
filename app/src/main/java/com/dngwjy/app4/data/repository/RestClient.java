package com.dngwjy.app4.data.repository;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    //"https://api.myjson.com/"
    private static Retrofit.Builder builder() {
        return new Retrofit.Builder().baseUrl("http://192.168.137.220/app4CI/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create());
    }

    private static Retrofit retrofit() {
        return builder().build();
    }

    public static RestRepo restRepo() {
        return retrofit().create(RestRepo.class);
    }

}
