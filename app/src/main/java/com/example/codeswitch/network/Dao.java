package com.example.codeswitch.network;

import com.example.codeswitch.model.User;
import com.example.codeswitch.model.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Dao<T> {
    @GET("users/{id}")
    Call<User> getUserDetail(@Path("id") int id);

    @FormUrlEncoded
    @POST("users/login")
    Call<AuthResponse> loginUser(@Field("email") String email,
                                 @Field("password") String password);
}
