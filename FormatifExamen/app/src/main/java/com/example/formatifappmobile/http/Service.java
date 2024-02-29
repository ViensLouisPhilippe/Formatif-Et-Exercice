package com.example.formatifappmobile.http;

import com.example.formatifappmobile.transfert.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("Pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") String id);
}
