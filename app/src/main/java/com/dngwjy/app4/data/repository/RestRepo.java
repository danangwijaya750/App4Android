package com.dngwjy.app4.data.repository;

import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.models.KasModel;
import com.dngwjy.app4.data.models.MasjidModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestRepo {
    @GET("Masjid")
    Call<List<MasjidModel>> getMasjid();

    @GET("/bins/175n1u")
    Call<List<KasModel>> getKasMasjid();

    @GET("Event")
    Call<List<EventModel>> getEvent();

    @GET("Event/getByMasjid/{id}")
    Call<List<EventModel>>eventOfMasjid(@Path("id")String id);


}
