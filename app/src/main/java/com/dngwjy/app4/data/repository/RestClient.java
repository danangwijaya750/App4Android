package com.dngwjy.app4.data.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private  static Retrofit.Builder builder(){
        return new Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create());
    }

    private static Retrofit retrofit(){
        return  builder().build();
    }

    public static RestRepo restRepo(){
        return retrofit().create(RestRepo.class);
    }

}
