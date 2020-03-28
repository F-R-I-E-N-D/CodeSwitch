package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class TestCSA extends ModifiedActivity implements SearchActivity {
/*

    //skillSelectMenu setup
    Button mOrder;  //skillSelectButton
    String[] listItems; //skillList
    boolean[] checkedItems;   //selectedSkillsBoolean
    ArrayList<Integer> mUserItems = new ArrayList<>();  //userSelectedSkills

    //recyclerview setup
    private RecyclerView courseRecyclerView;
    private CourseRecyclerViewAdapter courseRecyclerAdapter;
    private RecyclerView.LayoutManager courseRecyclerManager;

    //courseItem setup
    private ArrayList<CourseItem> courseItems = new ArrayList<>();
    private ArrayList<CourseItem> filteredCourseItems = new ArrayList<>();
    private ArrayList <String> skillsToAdd = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //skillSelectMenu
        mOrder = findViewById(R.id.skill_select_menu);
        listItems = getResources().getStringArray(R.array.skills_list);
        checkedItems = new boolean[listItems.length];

        courseItems.clear();
        //ArrayList<CourseItem> courseItems = new ArrayList<>();
        //TEMP add dummyCourses
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

        //TEST
        filteredCourseItems.clear();
        filteredCourseItems.addAll(courseItems);



        //recyclerView Display
        courseRecyclerView = findViewById(R.id.recyclerView_courseSearch);
        courseRecyclerView.setHasFixedSize(true);
        courseRecyclerManager = new LinearLayoutManager(this);
        courseRecyclerAdapter = new CourseRecyclerViewAdapter(filteredCourseItems);

        courseRecyclerView.setLayoutManager(courseRecyclerManager);
        courseRecyclerView.setAdapter(courseRecyclerAdapter);
*//*
        //skillSelection
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder skillSelectBuilder = new AlertDialog.Builder(TestCSA.this);
                skillSelectBuilder.setTitle("Select Skills to Filter Search By");
                skillSelectBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            }
                        } else if (mUserItems.contains(position)) {
                            mUserItems.remove((Integer.valueOf(position)));
                        }
*/
/*                        if(isChecked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }*//*

                    }
                });
                skillSelectBuilder.setCancelable(false);
                skillSelectBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mUserItems.isEmpty()) {
                            Log.d("DEBUG", "mUserItems is not Empty");
                            //courseRecyclerAdapter.notifyDataSetChanged();

                            filteredCourseItems.clear();
                            skillsToAdd.clear();
                            for (int i = 0; i < mUserItems.size(); i++) {
                                skillsToAdd.add(listItems[mUserItems.get(i)]);
                                Log.d("DEBUG", "skills to add:"+listItems[mUserItems.get(i)]);
                            }
                            //TODO: this only works for a single skill. Needs to be changed to allow multiple skills

                            //filteredCourseItems
                            for (int i = 0; i < courseItems.size(); i++) {
                                boolean selected = false;
                                //filteredCourseItems.add(courseItems.get(i));
                                for (int j = 0; j < skillsToAdd.size(); j++) {
                                    if (courseItems.get(i).getCourseOrganizationText().equals(skillsToAdd.get(j))) {

                                        selected = true;
                                        break;
                                    }
                                }
                                if (selected) {
                                    filteredCourseItems.add(courseItems.get(i));
                                    Log.d("DEBUG", courseItems.get(i).getCourseTitleText()+"added");
                                }
                            }
                            courseRecyclerAdapter.notifyDataSetChanged();
                        }
                        else{
                            Log.d("DEBUG", "Checkboxes are empty");
                            filteredCourseItems.clear();
                            filteredCourseItems.addAll(courseItems);
                            courseRecyclerAdapter.notifyDataSetChanged();
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
                            filteredCourseItems.clear();
                            filteredCourseItems.addAll(courseItems);

                        }
                        courseRecyclerAdapter.notifyDataSetChanged();
                    }

                });
                AlertDialog mDialog = skillSelectBuilder.create();
                mDialog.show();
            }


        });
*/
/*
        //searchView & Filter
        SearchView searchView = findViewById(R.id.action_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                courseRecyclerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //courseRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });*//*




        //navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.ic_job_search:
                        Intent intent_toJS = new Intent(TestCSA.this, JobSearchActivity.class);
                        startActivity(intent_toJS);
                        break;
                    case R.id.ic_course_search:
                        //already here
                        break;
                    case R.id.ic_saved_jobs:
                        Intent intent_toSJ = new Intent(TestCSA.this, SavedJobsActivity.class);
                        startActivity(intent_toSJ);
                        break;
                    case R.id.ic_profile:
                        Intent intent_toEP = new Intent(TestCSA.this, EditProfileActivity.class);
                        startActivity(intent_toEP);
                        break;
                }

                return false;
            }
        });



    }

    //selectSkillsMenu functions
*/
/*

    public void selectContextMenu(View view){
        registerForContextMenu(view);
        openContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater skillsMenuInflater = getMenuInflater();
        skillsMenuInflater.inflate(R.menu.skill_select_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.skill_a:
                Toast.makeText(getApplicationContext(), "Skill A selected.", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                return true;
            case R.id.skill_b:
                Toast.makeText(getApplicationContext(), "Skill B selected.", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                return true;
            case R.id.skill_c:
                Toast.makeText(getApplicationContext(), "Skill C selected.", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                return true;
            case R.id.select:
                Toast.makeText(getApplicationContext(), "Select selected.", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                return true;
        }
        return super.onContextItemSelected(item);
    }*//*


//endmenu

*/
    @Override
    public void fetchDisplayItems(String keyword) {

    }

    @Override
    public void displayItems() {

    }



}
