package com.practice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.R;
import com.practice.Response.News;
import com.practice.adapter.NewsAdapter;
import com.practice.retrofit.APIClient;
import com.practice.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements NewsAdapter.ItemClickListener {
    private TextView textViewTotalBook;
    private RecyclerView recycleView;
    private ApiInterface apiInterface;
    private NewsAdapter newsAdapter;
    private TextView emptyResponseTextView;

    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        textViewTotalBook = view.findViewById(R.id.total_news);
        recycleView = view.findViewById(R.id.news_recycle_view);
        emptyResponseTextView = view.findViewById(R.id.empty_news_view);
        apiInterface = APIClient.getRetrofitClientForNews().create(ApiInterface.class);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getNews();
    }

    private void getNews() {
        Call<News> call = apiInterface.getNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                setData(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    private void setData(News news) {
        if(news != null) {
            textViewTotalBook.setText("Total news : " + news.getTotalResults());

            recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            newsAdapter = new NewsAdapter(getContext(), news.getArticles());
            newsAdapter.setClickListener(this);
            recycleView.setAdapter(newsAdapter);
        } else {
                emptyResponseTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
