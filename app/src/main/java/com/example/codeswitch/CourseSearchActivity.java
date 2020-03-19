package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CourseSearchActivity extends ModifiedActivity implements SearchActivity {

    //recyclerview setup
    private RecyclerView courseRecyclerView;
    private CourseRecyclerViewAdapter courseRecyclerAdapter;
    private RecyclerView.LayoutManager courseRecyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);


        ArrayList<CourseItem> courseItems = new ArrayList<>();
        //dummyCourses
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 1", "Skill A"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 2", "Skill B"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 3", "Skill C"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 4", "Skill A"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course java", "Skill B"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course python", "Skill C"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course C++", "Skill A"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course java 2", "Skill B"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course python 2", "Skill C"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 10", "Skill A"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 11", "Skill B"));
        courseItems.add(new CourseItem(R.drawable.sample_tech_image, "Course 12", "Skill C"));

        courseRecyclerView = findViewById(R.id.recyclerView_courseSearch);
        courseRecyclerView.setHasFixedSize(true);
        courseRecyclerManager = new LinearLayoutManager(this);
        courseRecyclerAdapter = new CourseRecyclerViewAdapter(courseItems);

        courseRecyclerView.setLayoutManager(courseRecyclerManager);
        courseRecyclerView.setAdapter(courseRecyclerAdapter);

        SearchView searchView = findViewById(R.id.action_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                courseRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });

        //navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
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
                        break;
                    case R.id.ic_saved_jobs:
                        Intent intent_toSJ = new Intent(CourseSearchActivity.this, SavedJobsActivity.class);
                        startActivity(intent_toSJ);
                        break;
                    case R.id.ic_profile:
                        Intent intent_toEP = new Intent(CourseSearchActivity.this, EditProfileActivity.class);
                        startActivity(intent_toEP);
                        break;
                }

                return false;
            }
        });



    }

    @Override
    public void fetchDisplayItems(String keyword) {

    }

    @Override
    public void displayItems() {

    }


}
