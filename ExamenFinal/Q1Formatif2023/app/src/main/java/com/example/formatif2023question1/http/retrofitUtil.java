package com.example.formatif2023question1.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class retrofitUtil {
    public static com.example.formatif2023question1.http.Service get(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://examen-final-h23.azurewebsites.net/")
                .build();

        com.example.formatif2023question1.http.Service service = retrofit.create(Service.class);
        return service;
    }
}
