package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SavedJobsActivity extends ModifiedActivity {

    //recyclerview setup

    private ArrayList<JobItem> jobItems;

    private RecyclerView savedJobsRecyclerView;
    private RecyclerView.Adapter savedJobsRecyclerAdapter;
    private RecyclerView.LayoutManager savedJobsRecyclerManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);

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




        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
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

    public void createJobsList(){
        jobItems = new ArrayList<>();
        //dummyJobs
        jobItems.add(new JobItem(R.drawable.sample_tech_image, "Job 1", "Skill A"));
        jobItems.add(new JobItem(R.drawable.sample_tech_image, "Job 2", "Skill B"));
        jobItems.add(new JobItem(R.drawable.sample_tech_image, "Job 3", "Skill C"));
    }

    public void buildRecyclerView(){
        savedJobsRecyclerView = findViewById(R.id.recyclerView_savedJobs);
        savedJobsRecyclerView.setHasFixedSize(true);
        savedJobsRecyclerManager = new LinearLayoutManager(this);
        savedJobsRecyclerAdapter = new JobRecyclerViewAdapter(jobItems);

        savedJobsRecyclerView.setLayoutManager(savedJobsRecyclerManager);
        savedJobsRecyclerView.setAdapter(savedJobsRecyclerAdapter);
    }

    public void insertItem(int position){
        jobItems.add(position, new JobItem(R.drawable.sample_tech_image, "New Job: pos "+position, "ReqSkill?"));
        savedJobsRecyclerAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        jobItems.remove(position);
        savedJobsRecyclerAdapter.notifyItemRemoved(position);
    }

}
