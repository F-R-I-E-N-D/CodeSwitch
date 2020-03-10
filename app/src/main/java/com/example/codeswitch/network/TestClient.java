package com.example.codeswitch.network;

import android.util.Log;
import android.widget.Toast;

import com.example.codeswitch.model.User;
import com.example.codeswitch.model.UserAuthResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestClient {

    public static void main(String[] args) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<User> call = apiInterface.getUserDetail(1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Network failure");
            }
        });
    }

    public static void run() {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<User> call = apiInterface.getUserDetail(1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.d("Debug", user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Network failure");
            }
        });
    }

    public static void runLogin() {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<UserAuthResponse> call = apiInterface.loginUser("admin@codeswitch.com", "admin");
//        System.out.println(call.toString());
        call.enqueue(new Callback<UserAuthResponse>() {
            @Override
            public void onResponse(Call<UserAuthResponse> call, Response<UserAuthResponse> response) {
                UserAuthResponse userAuthResponse = response.body();
                Log.d("Debug", userAuthResponse.toString());
            }

            @Override
            public void onFailure(Call<UserAuthResponse> call, Throwable t) {
                System.out.println("Network failure");
            }
        });
    }
}
