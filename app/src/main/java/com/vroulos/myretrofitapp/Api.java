package com.vroulos.myretrofitapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> createuser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
            );
}
