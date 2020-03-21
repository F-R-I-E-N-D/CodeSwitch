package com.example.codeswitch;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.TextView;

import com.example.codeswitch.model.Course;
import com.example.codeswitch.model.Interest;
import com.example.codeswitch.model.Skill;

import java.util.ArrayList;
import java.util.Date;

public class JobDetailsActivity extends ModifiedActivity implements DetailsActivity {

    String gameState, role, description, company, application_url, picture_url;
    Date date_posted;
    ArrayList<Interest> fields;
    ArrayList<Skill> requiredSkills;
    ArrayList<Course> recommendedCourses;
    Intent intent = getIntent();
    TextView roleTextView, descriptionTextView, companyTextView, application_urlTextView, picture_urlTextView, dateTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details);
        getDetails();
        display();
        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml fil


        //Bundle extras =intent.getExtras();




    }

/*
    protected void onDestroy();{

    }
*/
    public void getDetails(){
        role = "1";
        description = "2";
        company = "3";
        application_url = "4";
        date_posted = new Date();
    }

    public void display(){
        roleTextView = findViewById(R.id.JobTitle);
        roleTextView.setText(role);
        companyTextView = findViewById(R.id.CompanyName);
        companyTextView.setText(company);
        descriptionTextView = findViewById(R.id.JobDescription);
        descriptionTextView.setText(description);
        application_urlTextView = findViewById(R.id.JobURL);
        application_urlTextView.setText(application_url);
        //picture_urlTextView = (TextView) findViewById((R.id.JobImage);
        //picture_urlTextView.set(picture_url);
        dateTextView = findViewById(R.id.DatePosted);
        dateTextView.setText(date_posted.toString());
    }
/*
    public void OnSaveJobClick(){

    }

    public void OnBackClick(){
        this.finish();
    }
 */
}

