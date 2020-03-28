package com.example.codeswitch;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestAPI {

    Context thisContext = new ModifiedActivity();
    JSONArray searchResults;

    public static void main( String[] args)
    {
        TestAPI testAPI = new TestAPI();
        testAPI.getSolutionAPI();
    }

    public void getSolutionAPI()
    {
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(thisContext);

        Uri.Builder builder = new Uri.Builder();

        https://w5fe0239ih.execute-api.us-east-1.amazonaws.com/default/CodeSwitch?searchOrDetails=details&referenceNumber=NTU-200604393R-01-NC-IT1024

        builder.scheme("https")
                .authority("w5fe0239ih.execute-api.us-east-1.amazonaws.com")
                .appendPath("default")
                .appendPath("CodeSwitch")
                .appendQueryParameter("searchOrDetails", "search")
                .appendQueryParameter("keyword" , "knitting");

        String myUrl = builder.build().toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        System.out.println("Response: " + response.toString());
                        try {
                            searchResults = response.getJSONArray("body");// getString("solvedPuzzle");
                            System.out.println(searchResults.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("HTTPS Error: " + error.getMessage());
//                        Toast.makeText(thisContext, "Server Error, Solution Unavailable",Toast.LENGTH_SHORT).show();
                    }
                });

        ExampleRequestQueue.add(jsonObjectRequest);
    }
}
