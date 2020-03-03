package com.example.codeswitch;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ModifiedActivity {

    private String username;
    private String password;
    private boolean successful  = false; // Initialised
    private boolean prev = false;
    private boolean current = true;

    Context thisContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLoginClick (View view)
    {
        username = getEditText(R.id.loginUsername);
        password = getEditText(R.id.passwordLogin);

        // new LoginValidation().execute(username, password);

        //--------------------------//
        // Not async

        authenticateInBackground(username, password);

        System.out.println("Successful2: " + successful);
        if (successful)
        {
            Toast.makeText(thisContext, "Logged In",   Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(thisContext, "Unsuccessful Login",   Toast.LENGTH_SHORT).show();
        }
        //--------------------------//
    }

    public void onRegisterNewClick (View view)
    {
        Toast.makeText(this, "Register New",   Toast.LENGTH_LONG).show();
    }

    public boolean authenticateInBackground(String username, String password)
    {
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("o0g59esd92.execute-api.us-east-1.amazonaws.com")
                .appendPath("default")
                .appendPath("calTry")
                .appendQueryParameter("username", username)
                .appendQueryParameter("password", password);

        String myUrl = builder.build().toString();
        System.out.println(myUrl);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        System.out.println("xesponse: " + response.toString());
                        try {
                            current =!current;
                            System.out.println("Successful0: " + response.getBoolean("successful"));
                            successful =  response.getBoolean("successful");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(thisContext, "Server Error",   Toast.LENGTH_LONG).show();
                        System.out.println("HTTPS Error: " + error.getMessage());
                    }
                });

        while (jsonObjectRequest==null);

        System.out.println("JSON"+ jsonObjectRequest.getBody());
        System.out.println("Successful1: " + successful);
        ExampleRequestQueue.add(jsonObjectRequest);
        return successful;
    }

    private class LoginValidation extends AsyncTask<String, Void, Boolean>
    {
        @Override // Only one to run in background thread
        protected Boolean doInBackground(String ... strings) {

            return authenticateInBackground(strings[0], strings[1]);
        }

        @Override
        protected void onProgressUpdate(Void... values) { //Runs on UI thread
            super.onProgressUpdate(values); // replace this with your own
        }

        @Override
        protected void onPostExecute(Boolean bool) { //Runs on UI thread

            System.out.println("Successful2: " + successful);
            if (successful)
            {
                Toast.makeText(thisContext, "Logged In",   Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(thisContext, "Unsuccessful Login",   Toast.LENGTH_SHORT).show();
            }
        }
    }
}
