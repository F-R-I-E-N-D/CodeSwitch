package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class JobSearchActivity extends AppCompatActivity implements SearchActivity, JobRecyclerViewAdapter.OnJobListener {

    //set up API call
    Context thisContext = this;
    JSONArray searchResults = null;

    //set up JobItem objects
    private ArrayList<JobItem> jobItems = new ArrayList<>();
    private ArrayList<JobItem> filteredJobItems = new ArrayList<>();
    private ArrayList <String> fieldsToAdd = new ArrayList<String>();

    //set up RecyclerView
    private RecyclerView jobRecyclerView;
    private JobRecyclerViewAdapter jobRecyclerAdapter;
    private RecyclerView.LayoutManager jobRecyclerManager;


    //set up button menu selection
    Button mOrder;  //skillSelectButton
    String[] menuListItems; //skillList
    boolean[] checkedItems;   //selectedSkillsBoolean
    ArrayList<Integer> mUserItems = new ArrayList<>();  //userSelectedSkills

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);

        mOrder = findViewById(R.id.job_search_side_menu);
        menuListItems = getResources().getStringArray(R.array.skills_list);
        checkedItems = new boolean[menuListItems.length];


        //implement side menu
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder skillSelectBuilder = new AlertDialog.Builder(JobSearchActivity.this);
                skillSelectBuilder.setTitle("Select Skills to Filter Search By");
                skillSelectBuilder.setMultiChoiceItems(menuListItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            Log.d("SideMenu", position + " was checked");
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            }
                        } else if (mUserItems.contains(position)) {
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });
                skillSelectBuilder.setCancelable(false);
                skillSelectBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mUserItems.isEmpty()) {
                            Log.d("DEBUG", "mUserItems is not Empty");
                            //jobRecyclerAdapter.notifyDataSetChanged();

                            filteredJobItems.clear();
                            fieldsToAdd.clear();
                            for (int i = 0; i < mUserItems.size(); i++) {
                                fieldsToAdd.add(menuListItems[mUserItems.get(i)]);

                                Log.d("DEBUG", "Menu Items Selected:"+menuListItems[mUserItems.get(i)]);

                            }
                            //TODO: this only works for a single skill. Needs to be changed to allow multiple skills

                            //filter Course Items by comparing two lists (does nothing right now)
                            for (int i = 0; i < jobItems.size(); i++) {
                                boolean selected = false;

                                for (int j = 0; j < fieldsToAdd.size(); j++) {
                                    if (jobItems.get(i).getJobCompanyText().equals(fieldsToAdd.get(j))) {

                                        selected = true;
                                        break;
                                    }
                                }
                                if (selected) {
                                    filteredJobItems.add(jobItems.get(i));
                                    Log.d("DEBUG", jobItems.get(i).getJobTitleText()+"added");
                                }
                            }
                            jobRecyclerAdapter.notifyDataSetChanged();
                        }
                        else{
                            Log.d("DEBUG", "Checkboxes are empty");
                            filteredJobItems.clear();
                            filteredJobItems.addAll(jobItems);
                            jobRecyclerAdapter.notifyDataSetChanged();
                        }

                    }
                });
                skillSelectBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                skillSelectBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            Log.d("Debug", "checkedItems Length:" + checkedItems.length);
                            mUserItems.clear();
                            //restore full list of courseitems
                            filteredJobItems.clear();
                            filteredJobItems.addAll(jobItems);

                        }
                        jobRecyclerAdapter.notifyDataSetChanged();
                    }

                });
                AlertDialog mDialog = skillSelectBuilder.create();
                mDialog.show();
            }


        });


        //searchView
        SearchView searchView = findViewById(R.id.job_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                jobItems.clear();

                //dummy
                jobItems.add(
                        new JobItem(
                                R.drawable.job_img, "100",
                                new ArrayList<>(Arrays.asList("Skill A", "Skill B")),
                                "Software Engineer",
                                "Google",
                                "20-03-27"));
                jobItems.add(
                        new JobItem(
                                R.drawable.job_img,
                                "101",
                                new ArrayList<>(Arrays.asList("Skill A", "Skill B", "Skill C")),
                                "Data Scientist",
                                "Google",
                                "20-04-16"));
                jobItems.add(
                        new JobItem(
                                R.drawable.job_img,
                                "102",
                                new ArrayList<>(Arrays.asList("Skill A")),
                                "Business Analyst",
                                "Amazon",
                                "20-01-31"));
                jobItems.add(
                        new JobItem(
                                R.drawable.job_img,
                                "103",
                                new ArrayList<>(Arrays.asList("Skill B", "Skill C")),
                                "Structured Trader",
                                "Tesla",
                                "20-02-12"));
                jobItems.add(
                        new JobItem(
                                R.drawable.job_img,
                                "104",
                                new ArrayList<>(Arrays.asList("Skill A", "Skill C")),
                                "Wealth Manager",
                                "Visa",
                                "20-03-10"));
                jobRecyclerAdapter.notifyDataSetChanged();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        //recyclerview
        jobRecyclerView = findViewById(R.id.recyclerView_jobSearch);
        jobRecyclerView.setHasFixedSize(true);
        jobRecyclerManager = new LinearLayoutManager(this);
        jobRecyclerAdapter = new JobRecyclerViewAdapter(jobItems, this);   //pass the interface to the adapter

        jobRecyclerView.setLayoutManager(jobRecyclerManager);
        jobRecyclerView.setAdapter(jobRecyclerAdapter);



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.ic_job_search:
                        //already here
                        break;
                    case R.id.ic_course_search:
                        Intent intent_toCS = new Intent(JobSearchActivity.this, CourseSearchActivity.class);
                        startActivity(intent_toCS);
                        break;
                    case R.id.ic_saved_jobs:
                        Intent intent_toSJ = new Intent(JobSearchActivity.this, SavedJobsActivity.class);
                        startActivity(intent_toSJ);
                        break;
                    case R.id.ic_profile:
                        Intent intent_toEP = new Intent(JobSearchActivity.this, EditProfileActivity.class);
                        startActivity(intent_toEP);
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onJobClick(int position) {
        //when you click on a job
        //try {

            Intent goToJobDetails = new Intent(JobSearchActivity.this, JobDetailsActivity.class);
            String str = jobItems.get(position).getJobReferenceNumberText();
            goToJobDetails.putExtra("jobSearchReferenceNumber" , str);
            startActivity(goToJobDetails);
        /*}
        //when json has been gotten from cal
        catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void fetchDisplayItems(String keyword) {

    }

    @Override
    public void displayItems() {

    }


}
