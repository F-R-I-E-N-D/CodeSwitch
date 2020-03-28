package com.example.codeswitch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.codeswitch.model.Course;
import com.example.codeswitch.model.Skill;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class CourseDetailsActivity extends ModifiedActivity implements DetailsActivity {

    private Context thisContext = this;
    private Intent thisIntent;
    private String referenceNumber;
    private JSONObject searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);

        thisIntent = getIntent();

        // TODO : Remove once intent added in course search

        referenceNumber = thisIntent.getStringExtra("referenceNumber");
//        referenceNumber = "NTU-200604393R-01-NC-IT1024";
        Log.i ("Reference Num: ", referenceNumber);
//
        getDetails();
    }


    public void getDetails() {
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(thisContext);

        Uri.Builder builder = new Uri.Builder();

        //https://w5fe0239ih.execute-api.us-east-1.amazonaws.com/default/CodeSwitch?searchOrDetails=details&referenceNumber=NTU-200604393R-01-NC-IT1024

        builder.scheme("https")
                .authority("w5fe0239ih.execute-api.us-east-1.amazonaws.com")
                .appendPath("default")
                .appendPath("CodeSwitch")
                .appendQueryParameter("searchOrDetails", "details")
                .appendQueryParameter("referenceNumber", referenceNumber);

        String myUrl = builder.build().toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        searchResults = response;
//                        try
//                        {
                            display();
//                            Log.i("Course Details", response.toString());
//                            response.getString("referenceNumber");
//                            printPrompt(R.id.courseName, searchResults.getString("title"));
//                            printPrompt(R.id.courseProvider, searchResults.getString("trainingProviderAlias"));
//                            printPrompt(R.id.courseDescriptionText, searchResults.getString("content"));
//                            printPrompt(R.id.courseURLButton, searchResults.getString("url"));
//                            printPrompt(R.id.phoneCourseDetails, "Phone:\n" + searchResults.getString("phoneNumber"));
//                            printPrompt(R.id.emailCourseDetails, "Email:\n" + searchResults.getString("email"));
//                            printPrompt(R.id.datePosted, "Date Posted: " + searchResults.getString("createDate").substring(0,10));
//                            printPrompt(R.id.priceCourseDetails, "Cost per Trainee: SGD " + searchResults.getString("totalCostOfTrainingPerTrainee"));

//                        }
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("HTTPS Error: " + error.getMessage());
                    }
                });

        ExampleRequestQueue.add(jsonObjectRequest);
    }

    public void display() {
        try {
            printPrompt(R.id.courseName, searchResults.getString("title"));
            printPrompt(R.id.courseProvider, searchResults.getString("trainingProviderAlias"));
            printPrompt(R.id.courseDescriptionText, searchResults.getString("content"));
            printPrompt(R.id.phoneCourseDetails, "Phone:\n" + searchResults.getString("phoneNumber"));
            printPrompt(R.id.emailCourseDetails, "Email:\n" + searchResults.getString("email"));
            printPrompt(R.id.datePosted, "Date Posted: " + searchResults.getString("createDate").substring(0, 10));
            printPrompt(R.id.priceCourseDetails, "Cost per Trainee: SGD " + searchResults.getString("totalCostOfTrainingPerTrainee"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void onClickURL (View view){

        String thisUrl = null;
        try {
            thisUrl = searchResults.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        Log.i("Course Details", "Link Clicked: " + thisUrl);

        Intent browserIntent = new Intent (Intent.ACTION_VIEW, Uri.parse(thisUrl));
        Toast.makeText(thisContext, "Redirecting to website", Toast.LENGTH_SHORT).show();
        startActivity (browserIntent);
    }



}
