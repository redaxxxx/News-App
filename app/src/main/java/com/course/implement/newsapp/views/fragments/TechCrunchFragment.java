package com.course.implement.newsapp.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.course.implement.newsapp.BuildConfig;
import com.course.implement.newsapp.NewsActivity;
import com.course.implement.newsapp.R;
import com.course.implement.newsapp.adapter.NewsAdapter;
import com.course.implement.newsapp.data.Article;
import com.course.implement.newsapp.databinding.FragmentTechCrunchBinding;
import com.course.implement.newsapp.utils.Config;
import com.course.implement.newsapp.viewmodel.NewsViewModel;
import com.course.implement.newsapp.viewmodel.NewsViewModelFactory;

import java.util.List;

public class TechCrunchFragment extends Fragment implements NewsAdapter.OnclickItemListener{

    private FragmentTechCrunchBinding binding;
    private NewsAdapter adapter;
    private NewsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech_crunch, container, false);

        NewsViewModelFactory factory = new NewsViewModelFactory(Config.application);
        viewModel = new ViewModelProvider(requireActivity(), factory).get(NewsViewModel.class);

        viewModel.getTechCrunchArticles(BuildConfig.NEWS_API_KEY).observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if (articles != null && articles.size() > 0){
                    prepareRecyclerView(articles);
                }
            }
        });
        return binding.getRoot();
    }

    private void prepareRecyclerView(List<Article> articleList) {
        binding.rvTechCrunchArticles.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL
                , false));

        binding.rvTechCrunchArticles.setHasFixedSize(true);
        binding.rvTechCrunchArticles.setItemAnimator(new DefaultItemAnimator());
        adapter = new NewsAdapter(requireContext(),this, articleList);

        binding.rvTechCrunchArticles.setAdapter(adapter);
    }
    @Override
    public void onClick(Article article) {
        Intent intent = new Intent(getContext(), NewsActivity.class);
        intent.putExtra(Config.NEWS_TITLE, article.getTitle());
        intent.putExtra(Config.NEWS_IMAGE, article.getUrlToImage());
        intent.putExtra(Config.NEWS_CONTENT, article.getContent());
        startActivity(intent);
    }
}