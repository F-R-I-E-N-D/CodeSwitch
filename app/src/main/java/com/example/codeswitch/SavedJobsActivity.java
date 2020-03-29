package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codeswitch.model.Job;
import com.example.codeswitch.model.SavedJob;
import com.example.codeswitch.model.User;
import com.example.codeswitch.network.ApiManager;
import com.example.codeswitch.network.CustomCallback;
import com.example.codeswitch.network.Dao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SavedJobsActivity extends ModifiedActivity implements SavedJobsRecyclerViewAdapter.OnJobListener {

    //recyclerview setup

    private ArrayList<JobItem> savedJobItems = new ArrayList<>();

    private RecyclerView savedJobsRecyclerView;
    private RecyclerView.Adapter savedJobsRecyclerAdapter;
    private RecyclerView.LayoutManager savedJobsRecyclerManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;


    private User thisUser;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);
        dao = ApiManager.getInstance().create(Dao.class);

        thisUser = getUserFromPrefs();

        createJobsList();
        buildRecyclerView();

        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });

        //recyclerview
        //recyclerview
        buildRecyclerView();

        // ==================================



        // ==================================

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.ic_job_search:
                        Intent intent_toJS = new Intent(SavedJobsActivity.this, JobSearchActivity.class);
                        startActivity(intent_toJS);
                        break;
                    case R.id.ic_course_search:
                        Intent intent_toCS = new Intent(SavedJobsActivity.this, CourseSearchActivity.class);
                        startActivity(intent_toCS);
                        break;
                    case R.id.ic_saved_jobs:
                        //already here
                        return true;
                    case R.id.ic_profile:
                        Intent intent_toEP = new Intent(SavedJobsActivity.this, EditProfileActivity.class);
                        startActivity(intent_toEP);
                        break;
                }

                return false;
            }
        });

    }
//Call<User> applyJob(@Path("id") int id, @Field("is_applied") Boolean is_applied);
    private void createJobsList() {
        ApiManager.callApi(dao.getUserSavedJobs(thisUser.getId()), new CustomCallback<List<SavedJob>>() {

            @Override
            public void onResponse(List<SavedJob> response) {
                if (response != null) {
                    for (SavedJob savedJob: response)
                    {
                        JobItem currentJobItem = new JobItem(
                                R.drawable.job_img,
                                String.valueOf(savedJob.getId()),
                                savedJob.getJob().getRequiredSkills(),
                                savedJob.getJob().getTitle(),
                                savedJob.getJob().getCompany(),
                                savedJob.getJob().getDatePosted(),
                                checkIfQualified(savedJob.getJob().getRequiredSkills()),
                                savedJob.getId()
                        );
                        if(savedJob.getHasApplied()){
                            currentJobItem.setAppliedStatus(true);
                        }

                        savedJobItems.add(currentJobItem);
                        Log.d("AppliedJobs", "AppliedStatus: "+ currentJobItem.getAppliedStatus());
                        Log.d("Debug", savedJob.getJob().toString());
                    }
                    savedJobsRecyclerAdapter.notifyDataSetChanged();
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }

    public void createSavedJobsList(){

    }

    public void buildRecyclerView(){
        savedJobsRecyclerView = findViewById(R.id.recyclerView_savedJobs);
        savedJobsRecyclerView.setHasFixedSize(true);
        savedJobsRecyclerManager = new LinearLayoutManager(this);
        savedJobsRecyclerAdapter = new SavedJobsRecyclerViewAdapter(savedJobItems, this);

        savedJobsRecyclerView.setLayoutManager(savedJobsRecyclerManager);
        savedJobsRecyclerView.setAdapter(savedJobsRecyclerAdapter);
    }

    private boolean checkIfQualified(List<String> jobSkills)
    {
        Set<String> jobSkillsSet = new HashSet<String>(jobSkills);
        Log.d("DEBUG", "jobSkills: " + jobSkills.toString());
        Set<String> userSkills = new HashSet<String>(thisUser.getSkills());
        Log.d("DEBUG", "userSkills: " + thisUser.getSkills().toString());

        jobSkillsSet.removeAll(userSkills);
        if (jobSkillsSet.size()==0)
        {
            Log.d("DEBUG", "qualified");
            return true;
        }

        else {
            Log.d("DEBUG", "not qualified");
            return false;
        }
    }

    public void insertItem(int position){
        /*jobItems.add(position, new JobItem(R.drawable.sample_tech_image, "New Job: pos "+position, "ReqSkill?"));*/
        savedJobsRecyclerAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        savedJobItems.remove(position);
        savedJobsRecyclerAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onJobClick(int position) throws IOException {

        Log.d("AppliedJobs", "AppliedStatus: "+ savedJobItems.get(position).getAppliedStatus());

        if(savedJobItems.get(position).getAppliedStatus()){
            ApiManager.callApi(dao.applyJob(savedJobItems.get(position).getSavedJobID(), false), new CustomCallback<SavedJob>()  {
                @Override
                public void onResponse(SavedJob response) {

                    Toast.makeText(getApplicationContext(), "Job Unapplied!", Toast.LENGTH_SHORT).show();
                }
            });
            savedJobItems.get(position).setAppliedStatus(false);
            savedJobsRecyclerAdapter.notifyDataSetChanged();
            System.out.println("SavedJobID:" +savedJobItems.get(position).getSavedJobID());
        }
        else{
            ApiManager.callApi(dao.applyJob(savedJobItems.get(position).getSavedJobID(), true), new CustomCallback<SavedJob>()  {
                @Override
                public void onResponse(SavedJob response) {
                    Toast.makeText(getApplicationContext(), "Job Applied!", Toast.LENGTH_SHORT).show();
                }
            });
            savedJobItems.get(position).setAppliedStatus(true);
            savedJobsRecyclerAdapter.notifyDataSetChanged();
        }
/*
        public Boolean getHasApplied() {
            return hasApplied;
        }

        public void setHasApplied(Boolean hasApplied) {
            this.hasApplied = hasApplied;
        }*/

/*        ApiManager.callApi(dao.applyJob(savedJobItems.get(position).getSavedJobID(), true), new CustomCallback<SavedJob>()  {
            @Override
            public void onResponse(SavedJob response) {
                Toast.makeText(getApplicationContext(), "Job Applied!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
