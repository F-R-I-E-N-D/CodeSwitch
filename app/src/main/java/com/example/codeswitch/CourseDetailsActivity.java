package com.example.codeswitch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codeswitch.model.Skill;
import com.example.codeswitch.model.User;

import java.util.ArrayList;
import java.util.Date;

public class CourseDetailsActivity extends ModifiedActivity implements DetailsActivity {

    String gameState, courseName, courseProvider, courseURL, picture_url, courseDescription;
    Double price;
    Date date_posted;
    ArrayList<Skill> taughtSkills;
//    ArrayList<Course> recommendedCourses;
    Intent intent = getIntent();
    TextView courseNameTextView, courseProviderTextView, courseURLTextView, picture_urlTextView, courseDescriptionTextView, priceTextView;
    Button backButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);


        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.course_details);
        getDetails();
        display();

        //Bundle extras =intent.getExtras();


    }



    public void getDetails(){

        courseName = "1";
        courseDescription = "2";
        courseProvider = "3";
        courseURL = "4";
        date_posted = new Date();
    }

    public void display(){
        courseNameTextView = findViewById(R.id.courseName);
        courseNameTextView.setText(courseName);
        courseProviderTextView = findViewById(R.id.courseProvider);
        courseProviderTextView.setText(courseProvider);
        courseURLTextView = findViewById(R.id.courseURLButton);
        courseURLTextView.setText(courseURL);
        courseDescriptionTextView = findViewById(R.id.courseDescriptionText);
        courseDescriptionTextView.setText(courseDescription);
        //priceTextView = findViewById(R.id.);
        //priceTextView.setText(price.toString());
        backButton = findViewById(R.id.courseDetailsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void OnGetRecommendationClick(){

    }


}
