package com.example.codeswitch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeswitch.model.AuthResponse;
import com.example.codeswitch.model.Job;
import com.example.codeswitch.model.User;
import com.example.codeswitch.network.ApiManager;
import com.example.codeswitch.network.CustomCallback;
import com.example.codeswitch.network.Dao;

import java.util.ArrayList;
import java.util.List;

public class JobDetailsActivity extends ModifiedActivity {// implements DetailsActivity {

    Job thisJob;
    String jobTitle, jobDescription, companyName, jobURL, date_posted;
    List<String> requiredSkills, unacquiredSkills, acquiredSkills;
    Boolean acquired;
    Intent intent;
    private Job serializedJob;
    TextView jobTitleTextView, jobDescriptionTextView, companyNameTextView, jobURLTextView, picture_urlTextView, dateTextView;
    Button backButton;
    User user;
    private Dao dao;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details);
        dao = ApiManager.getInstance().create(Dao.class);

        getDetails();
//        display();

        intent = getIntent();

        serializedJob = (Job)intent.getSerializableExtra("serializedJob");
        Log.d("DEBUG", Boolean.toString(serializedJob==null) );
        Log.i ("Reference Num: ", serializedJob.getTitle());

    }


    public void getDetails(){
        //thisJob  = (Job) intent.getSerializableExtra("job");
        Log.d("Debug", "In get details");

        // TODO: Hi yh you need to do an exception for when the job has no skills + when the user has no skills.
        // Cos otherwise the for loop wont work.
        ApiManager.callApi(dao.getJob(5), new CustomCallback<Job>() {
            @Override
            public void onResponse(Job response) {
                Log.d("Debug", response.toString());
                if (response != null) {
                    thisJob = response;
                    Log.d("Debug", thisJob.toString());
                    jobTitle = thisJob.getTitle();
                    jobDescription = thisJob.getDescription();
                    companyName = thisJob.getCompany();
                    jobURL = thisJob.getApplicationSrc();
                    date_posted = thisJob.getDatePosted();
                    requiredSkills = thisJob.getRequiredSkills();
                    user = getUserFromPrefs();
                    acquiredSkills = user.getSkills();
                    //get the unacquired skills

                    unacquiredSkills = new ArrayList<>();


                    for(String requiredSkill : requiredSkills){
                        acquired = false;
                        for(String acquiredSkill: acquiredSkills){
                            if(acquiredSkill.contentEquals(requiredSkill)){
                                acquired = true;
                            }
                        }
                        if(!acquired){
                            unacquiredSkills.add(requiredSkill);
                        }
                    }

                    display();
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });


    }

    public void display() {
        jobTitleTextView = findViewById(R.id.jobTitleText);
        jobTitleTextView.setText(jobTitle);
        companyNameTextView = findViewById(R.id.companyNameText);
        companyNameTextView.setText(companyName);
        jobDescriptionTextView = findViewById(R.id.jobDescriptionText);
        jobDescriptionTextView.setText(jobDescription);
        dateTextView = findViewById(R.id.datePostedText);
        dateTextView.setText(date_posted);
        // dynamically generate requiredSkills
        // i is counter for ID generation
        int i = 0;
        androidx.gridlayout.widget.GridLayout requiredSkillsGridLayout = findViewById(R.id.acquiredSkillsGridLayout);
        // set the number of columns used by the grid layout
        requiredSkillsGridLayout.setColumnCount(2);
        //generate buttons based on the number of skills
        for (final String acquiredSkill : acquiredSkills) {
            Button btn = new Button(this);
            btn.setId(i);
            btn.setTag(acquiredSkill);
            btn.setText(acquiredSkill);
            requiredSkillsGridLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent requiredSkillIntent = new Intent(JobDetailsActivity.this, CourseSearchActivity.class);
                    requiredSkillIntent.putExtra("Skill",acquiredSkill);
                    startActivity(requiredSkillIntent);
                }
            });
            i++;
        }
        //generate unacquired skills
        androidx.gridlayout.widget.GridLayout unacquiredSkillsGridLayout = findViewById(R.id.unacquiredSkillsGridLayout);
        // set the number of columns used by the grid layout
        unacquiredSkillsGridLayout.setColumnCount(2);
        //generate buttons based on the number of skills
        for (final String unacquiredSkill : unacquiredSkills) {
            Button btn = new Button(this);
            btn.setId(i);
            btn.setTag(unacquiredSkill);
            btn.setText(unacquiredSkill);

            unacquiredSkillsGridLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent requiredSkillIntent = new Intent(JobDetailsActivity.this, CourseSearchActivity.class);
                    requiredSkillIntent.putExtra("Skill",unacquiredSkill);
                    startActivity(requiredSkillIntent);
                }
            });
            i++;
        }

        backButton = findViewById(R.id.jobDetailsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //button to go to website
    public void onClickURL (View view){

        String thisUrl = null;


        Log.i("Job Details", "Link Clicked: " + jobURL);

        Intent browserIntent = new Intent (Intent.ACTION_VIEW, Uri.parse(jobURL));
        Toast.makeText(this, "Redirecting to website", Toast.LENGTH_SHORT).show();
        startActivity (browserIntent);
    }
    //button to save job
    public void onClickSaveJob(View view){
        // Invalid password
        ApiManager.callApi(dao.saveJob(user.getId(), thisJob.getId()), new CustomCallback<AuthResponse>() {
            @Override
            public void onResponse(AuthResponse response) {
                if (response != null) {
                    Log.d("Debug", response.toString());
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }




}
