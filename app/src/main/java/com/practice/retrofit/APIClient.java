package com.practice.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String base_url = "https://www.googleapis.com/";
    private static final String news_base_url = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofitForNews = null;

    public static Retrofit getRetrofitClient() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    //Base URL is not same. Hence, creating this second method.
    public static Retrofit getRetrofitClientForNews() {

        if (retrofitForNews == null) {
            retrofitForNews = new retrofit2.Retrofit.Builder()
                    .baseUrl(news_base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitForNews;
    }
}
