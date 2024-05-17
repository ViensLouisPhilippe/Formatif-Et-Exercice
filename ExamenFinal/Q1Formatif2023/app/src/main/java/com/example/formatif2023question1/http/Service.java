package com.example.formatif2023question1.http;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("Exam2023/{nombre}")
    Call<String> getNumber(@Path("nombre") String nombre);
}
