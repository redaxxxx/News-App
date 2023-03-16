package com.course.implement.newsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.course.implement.newsapp.adapter.Repository;
import com.course.implement.newsapp.data.Article;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private final Repository repository;
    public NewsViewModel(@NonNull Application application) {
        repository = new Repository(application);
    }

    public MutableLiveData<List<Article>> getTeslaArticles(String apiKey){
        return repository.getTeslaArticle(apiKey);
    }

    public MutableLiveData<List<Article>> getBusinessArticles(String apiKey){
        return repository.getBusinessArticle(apiKey);
    }

    public MutableLiveData<List<Article>> getTechCrunchArticles(String apiKey){
        return repository.getTechCrunchArticle(apiKey);
    }
}
