package com.example.pelletier2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {
    @GET("exam/representations/{number}")
    Call<List<Representation>> getNumberRepresentations(@Path("number") int number);
}

