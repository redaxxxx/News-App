package com.course.implement.newsapp.adapter;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.course.implement.newsapp.data.Article;
import com.course.implement.newsapp.data.Articles;
import com.course.implement.newsapp.retrofit.APIServices;
import com.course.implement.newsapp.retrofit.HTTP;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private MutableLiveData<List<Article>> allNewsLiveData;
    private MutableLiveData<List<Article>> teslaArticleLiveData;
    private MutableLiveData<List<Article>> businessArticleLiveData;
    private MutableLiveData<List<Article>> techCrunchArticleLiveData;

    public Repository(Application application){
        teslaArticleLiveData = new MutableLiveData<>();
        businessArticleLiveData = new MutableLiveData<>();
        techCrunchArticleLiveData = new MutableLiveData<>();
        allNewsLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Article>> getAllNews(String apiKey){
        Call<Articles> call = HTTP.create(APIServices.class).getAllNews(apiKey);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.body() != null){
                    List<Article> articles = response.body().getArticles();
                    if (articles == null){
                        allNewsLiveData.postValue(null);
                    }else {
                        if (articles.size() > 0){
                            allNewsLiveData.setValue(articles);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.e("Connection Failed", t.getLocalizedMessage());
            }
        });
        return allNewsLiveData;
    }
    public MutableLiveData<List<Article>> getTeslaArticle(String apiKey){
        Call<Articles> call = HTTP.create(APIServices.class).getTeslaArticles(apiKey);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.body() != null){
                    List<Article> articleList = response.body().getArticles();
                    if (articleList == null){
                        teslaArticleLiveData.postValue(null);
                    }else {
                        if (articleList.size() > 0){
                            teslaArticleLiveData.setValue(articleList);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.e("Connection Failed", t.getLocalizedMessage());
            }
        });
        return teslaArticleLiveData;
    }


    public MutableLiveData<List<Article>> getBusinessArticle(String apiKey){
        Call<Articles> call = HTTP.create(APIServices.class).getBusinessArticles(apiKey);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.body() != null){
                    List<Article> articleList = response.body().getArticles();
                    if (articleList == null){
                        businessArticleLiveData.postValue(null);
                    } else {
                        if (articleList.size() > 0){
                            businessArticleLiveData.setValue(articleList);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("Connection Failed", t.getLocalizedMessage());
            }
        });

        return businessArticleLiveData;
    }


    public MutableLiveData<List<Article>> getTechCrunchArticle(String apiKey){
        Call<Articles> call = HTTP.create(APIServices.class).getTechCrunchArticles(apiKey);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.body() != null){
                    List<Article> articleList = response.body().getArticles();
                    if (articleList == null){
                        techCrunchArticleLiveData.postValue(null);
                    }else {
                        if (articleList.size() > 0){
                            techCrunchArticleLiveData.setValue(articleList);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

            }
        });
        return techCrunchArticleLiveData;
    }


}
