package com.example.codeswitch;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import com.example.codeswitch.model.Interest;
import com.example.codeswitch.model.Skill;

import java.util.ArrayList;
import java.util.Date;

public class JobDetailsActivity extends ModifiedActivity {// implements DetailsActivity {

    String jobTitle, jobDescription, companyName, jobURL, picture_url;
    Date date_posted;
    ArrayList<Interest> fields;
    ArrayList<Skill> requiredSkills;
    Intent intent = getIntent();
    TextView jobTitleTextView, jobDescriptionTextView, companyNameTextView, jobURLTextView, picture_urlTextView, dateTextView;
    Button backButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details);
        getDetails();
//        display();
        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml fil


        //Bundle extras =intent.getExtras();




    }

/*
    protected void onDestroy();{

    }
*/
    public void getDetails(){

        jobTitle = "1";
        jobDescription = "sample sample sample sample sample sample sample sample sample sample sample sample sample ";
        companyName = "3";
        jobURL = "4";
        date_posted = new Date();
    }

    public void display(){
        jobTitleTextView = findViewById(R.id.jobTitle);
        jobTitleTextView.setText(jobTitle);
        companyNameTextView = findViewById(R.id.companyName);
        companyNameTextView.setText(companyName);
        jobDescriptionTextView = findViewById(R.id.jobDescriptionText);
        jobDescriptionTextView.setText(jobDescription);
        jobURLTextView = findViewById(R.id.jobURLButton);
        jobURLTextView.setText(jobURL);
        //picture_urlTextView = (TextView) findViewById((R.id.JobImage);
        //picture_urlTextView.set(picture_url);
        dateTextView = findViewById(R.id.datePosted);
        dateTextView.setText(date_posted.toString());
        // dynamically generate requiredSkills
        int i = 0;
        androidx.gridlayout.widget.GridLayout gl = findViewById(R.id.jobSkillsGridLayout);
        gl.setColumnCount(3);
        for (i=0;i<20;i++) {
            Button btn = new Button(this);
            btn.setId(i+1000);
//            btn.setTag(requiredSkill.getName());
//            btn.setText(requiredSkill.getName());
            btn.setText("test" + i);
            int buttonId = btn.getId();
//            btn.setLayoutParams();

            gl.addView(btn);
//            btn.setOnClickListener(new View.OnClickListener() {
//            });
//            i++;
        }
        backButton = findViewById(R.id.jobDetailsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
    public void onClickFinish(View view){

    }


/*    public void generateCourses(){
        for (Course course : recommendedCourses){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("button " + id_);
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            linear.addView(btn, params);
            btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
        }
    }*/


}
