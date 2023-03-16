package com.course.implement.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import com.course.implement.newsapp.adapter.NewsAdapter;
import com.course.implement.newsapp.data.Article;
import com.course.implement.newsapp.databinding.ActivityNewsBinding;
import com.course.implement.newsapp.utils.Config;
import com.course.implement.newsapp.viewmodel.NewsViewModel;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ActivityNewsBinding binding;
    private NewsAdapter adapter;
    private NewsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        Intent intent = getIntent();

        if (intent != null){
            Picasso.with(this)
                    .load(intent.getStringExtra(Config.NEWS_IMAGE))
                    .into(binding.imageViewDetails);

            binding.newsTitle.setText(intent.getStringExtra(Config.NEWS_TITLE));
            binding.newsContent.setText(intent.getStringExtra(Config.NEWS_CONTENT));
        }

    }


}