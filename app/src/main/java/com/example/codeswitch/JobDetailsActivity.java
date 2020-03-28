package com.example.codeswitch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeswitch.model.Job;
import com.example.codeswitch.model.User;

import java.util.List;

public class JobDetailsActivity extends ModifiedActivity {// implements DetailsActivity {

    Job job = new Job();

    String jobTitle, jobDescription, companyName, jobURL, date_posted;
    List<String> requiredSkills, unacquiredSkills, acquiredSkills;
    Boolean acquired;
    Intent intent = getIntent();
    TextView jobTitleTextView, jobDescriptionTextView, companyNameTextView, jobURLTextView, picture_urlTextView, dateTextView;
    Button backButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details);
        referenceNumber = thisIntent.getStringExtra("referenceNumber");
//        referenceNumber = "NTU-200604393R-01-NC-IT1024";
        Log.i ("Reference Num: ", referenceNumber);
        getDetails();
//        display();
        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml fil


        //




    }

/*
    protected void onDestroy();{

    }
*/
    public void getDetails(){
        Job thisJob  = (Job) intent.getSerializableExtra("job");

        jobTitle = "1";
        jobDescription = "sample sample sample sample sample sample sample sample sample sample sample sample sample ";
        companyName = "3";
        jobURL = "4";

        jobTitle = thisJob.getTitle();
        jobDescription = thisJob.getDescription();
        companyName = thisJob.getCompany();
        jobURL = thisJob.getApplicationSrc();
        date_posted = thisJob.getDatePosted();
        requiredSkills = thisJob.getRequiredSkills();
        User user = getUserFromPrefs();
        acquiredSkills = user.getSkills();
        //get the unacquired skills

        for(String requiredSkill : requiredSkills){
            for(String acquiredSkill: acquiredSkills){
                if(acquiredSkill.contentEquals(requiredSkill)){
                    acquired = true;
                }
            }
            if(!acquired){
                unacquiredSkills.add(requiredSkill);
            }
        }

    }

    public void display() {
        jobTitleTextView = findViewById(R.id.jobTitle);
        jobTitleTextView.setText(jobTitle);
        companyNameTextView = findViewById(R.id.companyName);
        companyNameTextView.setText(companyName);
        jobDescriptionTextView = findViewById(R.id.jobDescriptionText);
        jobDescriptionTextView.setText(jobDescription);
        dateTextView = findViewById(R.id.datePosted);
        dateTextView.setText(date_posted);
        // dynamically generate requiredSkills
        // i is counter for ID generation
        int i = 0;
        androidx.gridlayout.widget.GridLayout requiredSkillsGridLayout = findViewById(R.id.requiredSkillsGridLayout);
        // set the number of columns used by the grid layout
        requiredSkillsGridLayout.setColumnCount(3);
        //generate buttons based on the number of skills
        for (final String requiredSkill : requiredSkills) {
            Button btn = new Button(this);
            btn.setId(i);
            btn.setTag(requiredSkill);
            btn.setText(requiredSkill);
            int buttonId = btn.getId();
            btn.setLayoutParams();

            requiredSkillsGridLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent requiredSkillIntent = new Intent(JobDetailsActivity.this, CourseSearchActivity.class);
                    requiredSkillIntent.putExtra("Skill",requiredSkill);
                    startActivity(requiredSkillIntent);
                }
            });
            i++;
        }
        //generate unacquired skills
        androidx.gridlayout.widget.GridLayout unacquiredSkillsGridLayout = findViewById(R.id.requiredSkillsGridLayout);
        // set the number of columns used by the grid layout
        unacquiredSkillsGridLayout.setColumnCount(3);
        //generate buttons based on the number of skills
        for (final String requiredSkill : requiredSkills) {
            Button btn = new Button(this);
            btn.setId(i);
            btn.setTag(requiredSkill);
            btn.setText(requiredSkill);

            unacquiredSkillsGridLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent requiredSkillIntent = new Intent(JobDetailsActivity.this, CourseSearchActivity.class);
                    requiredSkillIntent.putExtra("Skill",requiredSkill);
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
        user.;

    }




}
