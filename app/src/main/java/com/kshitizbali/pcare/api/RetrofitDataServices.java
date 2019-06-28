package com.kshitizbali.pcare.api;

import com.kshitizbali.pcare.model.RetroGetAppointments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitDataServices {

    /*@GET("06042019/CA/")*/
    @GET("{date}/CA/")
    Call<RetroGetAppointments> getAllAppointments(@Path("date") String date);
}
