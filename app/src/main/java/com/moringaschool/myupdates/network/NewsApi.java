package com.moringaschool.myupdates.network;


import com.moringaschool.myupdates.models.NewsUpdatesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("everything")
    Call<NewsUpdatesSearchResponse> getNews(
            @Query("domains") String domains,
            @Query("apiKey") String apiKey
    );
}
//https://newsapi.org/v2/top-headlines
