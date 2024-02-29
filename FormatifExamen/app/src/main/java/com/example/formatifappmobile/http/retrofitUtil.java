package com.example.formatifappmobile.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class retrofitUtil {
    public static com.example.formatifappmobile.http.Service get(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://examen-intra-h23.azurewebsites.net/")
                .build();

        com.example.formatifappmobile.http.Service service = retrofit.create(Service.class);
        return service;
    }
}
