package com.course.implement.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.implement.newsapp.R;
import com.course.implement.newsapp.data.Article;
import com.course.implement.newsapp.databinding.NewsItemBinding;
import com.course.implement.newsapp.viewmodel.NewsViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static final String NULL_KEY = "null";
    private final List<Article> mArticles;
    private final Context mContext;
    private OnclickItemListener mOnclickItemListener;

    public NewsAdapter(Context context, OnclickItemListener onclickItemListener, List<Article> articles) {
        mContext = context;
        mArticles = articles;
        this.mOnclickItemListener = onclickItemListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        if (mArticles != null && mArticles.size() > 0) {
            final Article article = mArticles.get(position);
            if (article.getTitle() != null) {
                if (article.getTitle().equals("null")) {
                    mContext.getResources().getString(R.string.author_not_found);
                } else {
                    holder.binding.titleTv.setText(article.getTitle());
                }

                    if (article.getPublishedAt() != null && article.getUrl() != null &&
                            article.getUrlToImage() != null) {
                        if (article.getPublishedAt().equals("null")) {
                            holder.binding.publishedAtTv.setText(mContext.getResources().getString(R.string.author_not_found));
                        } else {
                            holder.binding.publishedAtTv.setText(article.getPublishedAt());
                        }

                        if (!article.getUrlToImage().isEmpty() && !article.getUrlToImage().equals("null")) {
                            Picasso.with(mContext)
                                    .load(article.getUrlToImage())
                                    .into(holder.binding.imageView);

                        }
                    } else {
                        holder.binding.publishedAtTv.setText("");
                    }
                }
            holder.itemView.setOnClickListener(view -> {
                    mOnclickItemListener.onClick(article);

            });
            }else {
                holder.binding.titleTv.setText("");
            }



    }


    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        NewsItemBinding binding;

        public NewsViewHolder(@NonNull NewsItemBinding newsItemBinding) {
            super(newsItemBinding.getRoot());
            binding = newsItemBinding;
        }
    }

    public interface OnclickItemListener{
        void onClick(Article article);
    }
}
