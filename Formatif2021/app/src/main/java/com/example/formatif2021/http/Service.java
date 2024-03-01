package com.example.formatif2021.http;

import com.example.formatif2021.transfert.DateFormatif;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("exam/{annee}/{mois}/{jour}")
    Call<List<DateFormatif>> getDates(@Path("annee") int annee, @Path("mois") int mois, @Path("jour") int jour );
}
