package com.practice.retrofit;

import com.practice.Response.Book;
import com.practice.Response.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("books/v1/volumes?q=1")
    Call<Book> getBook();

    @GET("top-headlines?sources=techcrunch&apiKey=KEY")
    Call<News> getNews();
}
