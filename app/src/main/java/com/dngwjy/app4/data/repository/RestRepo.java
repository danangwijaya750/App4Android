package com.dngwjy.app4.data.repository;

import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.models.KasModel;
import com.dngwjy.app4.data.models.MasjidModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestRepo {
    @GET("/")
    Call<List<MasjidModel>> getMasjid();

    @GET("/")
    Call<List<KasModel>> getKasMasjid();

    @GET("/")
    Call<List<EventModel>> getEvent();


}
