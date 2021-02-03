package com.moringaschool.myupdates.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.myupdates.R;
import com.moringaschool.myupdates.adapters.NewsListAdapter;
import com.moringaschool.myupdates.models.Article;
import com.moringaschool.myupdates.models.NewsUpdatesSearchResponse;
import com.moringaschool.myupdates.network.NewsApi;
import com.moringaschool.myupdates.network.NewsClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListActivity extends AppCompatActivity {
    public static final String TAG = NewsListActivity.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerview;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private NewsListAdapter mAdapter;

    public List<Article> top_headlines;

    //Override calling get methods in main layout and class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsupdate);
        ButterKnife.bind(this);

        //User's next page display welcome message, Using Intents.
        Log.v("NewsScopeActivity", "In the onItemClickListener!");
        final Intent intent = getIntent();
        String user = intent.getStringExtra("user");
//        mEditTextPersonName.setText("Welcome back " + user);

        NewsApi client = NewsClient.getClient();

        Call<NewsUpdatesSearchResponse> call = client.getNews("standardmedia.co.ke", "441e5fea5c6d4f29bee20f551a8cc836");


        call.enqueue(new Callback<NewsUpdatesSearchResponse>() {
            @Override
            public void onResponse(Call<NewsUpdatesSearchResponse> call, Response<NewsUpdatesSearchResponse> response) {
                if (response.isSuccessful()) {
                    hideProgressBar();
                    top_headlines = response.body().getArticles();
                    mAdapter = new NewsListAdapter(NewsListActivity.this, top_headlines);
                    mRecyclerview.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsListActivity.this);
                    mRecyclerview.setLayoutManager(layoutManager);
                    mRecyclerview.setHasFixedSize(true);
                    showTop_headlines();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<NewsUpdatesSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:", t);
                hideProgressBar();
                showFailureMessage();
            }
        });
    }



    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showTop_headlines() {
        mRecyclerview.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
