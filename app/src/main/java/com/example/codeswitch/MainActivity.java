package com.example.codeswitch;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.codeswitch.model.UserAuthResponse;
import com.example.codeswitch.network.ApiClient;
import com.example.codeswitch.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ModifiedActivity {
    private String email;
    private String password;
    private ApiInterface api;
    private UserAuthResponse userAuthResponse = null;

//    private boolean successful  = false; // Initialised
//    private boolean prev = false;
//    private boolean current = true;
//
    Context thisContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = ApiClient.getInstance().create(ApiInterface.class);

//        TestClient.runLogin();
    }

    public void onLoginClick(View view) {
        email = getEditText(R.id.email_login_input);
        password = getEditText(R.id.password_login_input);

        // new LoginValidation().execute(email, password);

        //--------------------------//
        // Not async

        authenticate(email, password);

//        System.out.println("Successful2: " + successful);
//        if (successful)
//        {
//            Toast.makeText(thisContext, "Logged In",   Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(thisContext, "Unsuccessful Login",   Toast.LENGTH_SHORT).show();
//        }
        //--------------------------//
    }

    public void onRegisterNewClick(View view) {
        Toast.makeText(this, "register_button clicked", Toast.LENGTH_SHORT).show();
    }

    // TODO: This uses the facade pattern!
    public void authenticate(String email, String password) {
        Call<UserAuthResponse> call = api.loginUser(email, password);

        call.enqueue(new Callback<UserAuthResponse>() {
            @Override
            public void onResponse(Call<UserAuthResponse> call, Response<UserAuthResponse> response) {
                userAuthResponse = response.body();
                System.out.println(userAuthResponse.toString());
                Toast.makeText(thisContext, userAuthResponse.getMessage(), Toast.LENGTH_LONG).show();

                if (userAuthResponse.getSuccess()) {
                    System.out.println("success!");
                    // do intent stuff
                }
            }

            @Override
            public void onFailure(Call<UserAuthResponse> call, Throwable t) {
                System.out.println("Network failure!");
            }
        });
    }
}