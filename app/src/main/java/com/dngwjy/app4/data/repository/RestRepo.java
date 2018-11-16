package com.dngwjy.app4.data.repository;

import com.dngwjy.app4.data.models.EventModel;
import com.dngwjy.app4.data.models.KasModel;
import com.dngwjy.app4.data.models.MasjidModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestRepo {
    @GET("/bins/1501ge")
    Call<List<MasjidModel>> getMasjid();

    @GET("/bins/175n1u")
    Call<List<KasModel>> getKasMasjid();

    @GET("/bins/175n1u")
    Call<List<EventModel>> getEvent();


}
