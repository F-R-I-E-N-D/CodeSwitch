package com.example.codeswitch;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CourseSearchActivity extends ModifiedActivity implements SearchActivity, CourseRecyclerViewAdapter.OnNoteListener {

    //set up API call
    Context thisContext = this;
    JSONArray searchResults = null;

    //set up CourseItem objects
    private ArrayList<CourseItem> courseItems = new ArrayList<>();

    //set up RecyclerView
    private RecyclerView courseRecyclerView;
    private CourseRecyclerViewAdapter courseRecyclerAdapter;
    private RecyclerView.LayoutManager courseRecyclerManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);

        //searchView
        SearchView searchView = findViewById(R.id.action_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                getCourseItemsFromAPI(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        //recyclerview
        courseRecyclerView = findViewById(R.id.recyclerView_courseSearch);
        courseRecyclerView.setHasFixedSize(true);
        courseRecyclerManager = new LinearLayoutManager(this);
        courseRecyclerAdapter = new CourseRecyclerViewAdapter(courseItems, this);   //pass the interface to the adapter

        courseRecyclerView.setLayoutManager(courseRecyclerManager);
        courseRecyclerView.setAdapter(courseRecyclerAdapter);

        //bottomnavigationview
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        displayBottomNavigationView(bottomNavigationView);

    }

    public void displayBottomNavigationView(BottomNavigationView bottomNavigationView){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_job_search:
                        Intent intent_toJS = new Intent(CourseSearchActivity.this, JobSearchActivity.class);
                        startActivity(intent_toJS);
                        break;
                    case R.id.ic_course_search:
                        //already here
                        return true;
                    case R.id.ic_saved_jobs:
                        Intent intent_toSJ = new Intent(CourseSearchActivity.this, SavedJobsActivity.class);
                        startActivity(intent_toSJ);
                        return true;
                    case R.id.ic_profile:
                        Intent intent_toEP = new Intent(CourseSearchActivity.this, EditProfileActivity.class);
                        startActivity(intent_toEP);
                        return true;
                }

                return false;
            }
        });
    }



    //get api courses
    public void getCourseItemsFromAPI(String keyword)
    {
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(thisContext);

        Uri.Builder builder = new Uri.Builder();

        //https://w5fe0239ih.execute-api.us-east-1.amazonaws.com/default/CodeSwitch?searchOrDetails=details&referenceNumber=NTU-200604393R-01-NC-IT1024

        builder.scheme("https")
                .authority("w5fe0239ih.execute-api.us-east-1.amazonaws.com")
                .appendPath("default")
                .appendPath("CodeSwitch")
                .appendQueryParameter("searchOrDetails", "search")
                .appendQueryParameter("keyword" , keyword);

        String myUrl = builder.build().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, myUrl, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        searchResults = response;
                        try {
                            courseItems.clear();
                            for (int i = 0; i<searchResults.length();i++){

                                courseItems.add(
                                        new CourseItem(
                                                R.drawable.sample_tech_image,
                                                searchResults.getJSONObject(i).getString("title"),
                                                searchResults.getJSONObject(i).getString("referenceNumber"),
                                                searchResults.getJSONObject(i).getString("trainingProviderAlias"),
                                                searchResults.getJSONObject(i).getString("modeOfTrainings")));
                            }


                            courseRecyclerAdapter.notifyDataSetChanged();

                            System.out.println(courseItems.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("HTTPS Error: " + error.getMessage());
                    }
                });

        ExampleRequestQueue.add(jsonArrayRequest);
    }


    @Override
    public void fetchDisplayItems(String keyword) {

    }

    @Override
    public void displayItems() {

    }


    @Override
    public void onNoteClick(int position) {
        try {
            Log.d("onCourseClick", searchResults.getJSONObject(position).getString("title")+ " at Index: " + position);
            Intent goToCourseDetails = new Intent(CourseSearchActivity.this, CourseDetailsActivity.class);
            String str = searchResults.getJSONObject(position).getString("referenceNumber");
            goToCourseDetails.putExtra("referenceNumber" , str);
            startActivity(goToCourseDetails);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
