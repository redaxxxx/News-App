package com.course.implement.newsapp.retrofit;

import com.course.implement.newsapp.data.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {

    @GET("v2/everything?q=tesla&from=2023-02-16&sortBy=publishedAt")
    Call<Articles> getTeslaArticles(@Query("apiKey") String apiKey);

    @GET("v2/top-headlines?country=us&category=business")
    Call<Articles> getBusinessArticles(@Query("apiKey") String apiKey);

    @GET("v2/top-headlines?sources=techcrunch")
    Call<Articles> getTechCrunchArticles(@Query("apiKey") String apiKey);


}
//https://newsapi.org/
// everything?q=tesla&from=2023-02-13&sortBy=publishedAt&apiKey=23a641b28b2544a5a43fe83c558cec39

// https://newsapi.org/v2/everything?
// q=apple&from=2023-03-12&to=2023-03-12&sortBy=popularity&apiKey=23a641b28b2544a5a43fe83c558cec39