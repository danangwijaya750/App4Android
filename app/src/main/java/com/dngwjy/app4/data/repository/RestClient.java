package com.dngwjy.app4.data.repository;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    //"https://api.myjson.com/"
    private static Retrofit.Builder builder() {
        return new Retrofit.Builder().baseUrl("http://10.1.9.36/app4CI/").addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));
    }

    private static Retrofit retrofit() {
        return builder().build();
    }

    public static RestRepo restRepo() {
        return retrofit().create(RestRepo.class);
    }

}
