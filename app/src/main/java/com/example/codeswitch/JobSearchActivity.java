package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JobSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);


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
}
