package com.example.retrofit_zadanie;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("db.json")
    Call<List<Wiadomosc>> getWiadomosci();
}
