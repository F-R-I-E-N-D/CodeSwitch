package com.example.codeswitch.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit instance;   // Singleton design pattern
    private static final String BASE_URL = "http://codeswitch-rest-api.herokuapp.com/";

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
