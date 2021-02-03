package com.moringaschool.myupdates.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myupdates.models.Article;
import com.moringaschool.myupdates.ui.NewsListActivity;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter {
    public NewsListAdapter(NewsListActivity newsListActivity, List<Article> top_headlines) {


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
