package com.vroulos.myretrofitapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.43.200/MyApi/Public/";
    private static RetrofitClient myInstance;
    private Retrofit retrofit;

    // cunstructor of the class
    private RetrofitClient(){
        // cre
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){

        if(myInstance == null){
            myInstance = new RetrofitClient();
        }
        return myInstance;

    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
