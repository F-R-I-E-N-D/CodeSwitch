package com.example.codeswitch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.codeswitch.model.AuthResponse;
import com.example.codeswitch.network.ApiManager;
import com.example.codeswitch.network.ApiTest;
import com.example.codeswitch.network.CustomCallback;
import com.example.codeswitch.network.Dao;

public class MainActivity extends ModifiedActivity {
    private String email;
    private String password;
    private Dao dao;

    Context thisContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This code must be here if you want to use the API.
        dao = ApiManager.getInstance().create(Dao.class);

        Log.d("Debug", "Hello!");
//        ApiTest.testGetJobs();
    }

    public void onLoginClick(View view) {
        email = getEditText(R.id.email_login_input);
        password = getEditText(R.id.password_login_input);
        //hotwire - yh
//        try {
//            Intent k = new Intent(MainActivity.this, JobDetailsActivity.class);
//            startActivity(k);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }


        authenticateLogin(email, password);
    }

    public void onRegisterNewClick(View view) {
        Intent k = new Intent(this, CreateAccountActivity.class);
        startActivity(k);
    }

    /**
     * Logs the user into the app. Validity check is done by server.
     * @param email User's inputted email
     * @param password User's inputted password
     */
    private void authenticateLogin(String email, String password) {
        ApiManager.callApi(dao.loginUser(email, password), new CustomCallback<AuthResponse>() {
            @Override
            public void onResponse(AuthResponse response) {
                if (response.getSuccess()) {
                    Log.d("Debug", response.toString());

                    //go to job search - tim, yh details
                    try {
                        saveUserToPrefs(response.getUser());
                        Intent k = new Intent(MainActivity.this, JobSearchActivity.class);
                        startActivity(k);
//                        Intent k = new Intent(MainActivity.this, JobSearchActivity.class);
//                        startActivity(k);
                        Intent k = new Intent(MainActivity.this, JobSearchActivity.class);
                        startActivity(k);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    //end tim

                } else {
                    Toast.makeText(thisContext, response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
