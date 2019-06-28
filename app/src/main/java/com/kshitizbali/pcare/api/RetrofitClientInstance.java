package com.kshitizbali.pcare.api;

import android.content.Context;
import android.widget.Toast;

import com.kshitizbali.pcare.database.AppDatabase;
import com.kshitizbali.pcare.database.AppExecutors;
import com.kshitizbali.pcare.database.AppointmentsEntry;
import com.kshitizbali.pcare.database.DbNetworkOperations;
import com.kshitizbali.pcare.model.DoctorModel;
import com.kshitizbali.pcare.model.RetroGetAppointments;
import com.kshitizbali.pcare.utilities.ConstantUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.plushcare.com/appointments/internal/next/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }



    public static void getAppointments(final Context context, String date) {
        RetrofitDataServices services = getRetrofitInstance().create(RetrofitDataServices.class);
        Call<RetroGetAppointments> call = services.getAllAppointments(date);
        call.enqueue(new Callback<RetroGetAppointments>() {
            @Override
            public void onResponse(Call<RetroGetAppointments> call, Response<RetroGetAppointments> response) {
                if (response.isSuccessful()) {
                    // Save in room DB
                    if (response.body() != null) {
                        DbNetworkOperations.insertAllAppointments(context, response);
                    }
                } else if (response.code() == 401) {
                    // Handle unauthorized
                    Toast.makeText(context, ConstantUtils.UNAUTHORIZED_ACCESS, Toast.LENGTH_SHORT).show();
                } else {
                    // Handle other responses
                    Toast.makeText(context, ConstantUtils.PLEASE_TRY_LATER, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetroGetAppointments> call, Throwable t) {

            }
        });

    }
}
