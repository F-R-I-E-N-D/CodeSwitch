package com.example.codeswitch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.codeswitch.model.Course;
import com.example.codeswitch.model.Skill;

import java.util.ArrayList;
import java.util.Date;

public class CourseDetailsActivity extends ModifiedActivity /*implements DetailsActivity*/ {

    String gameState, title, organizer, url, picture_url, description;
    Double price;
    Date date_posted;
    ArrayList<Skill> taughtSkills;
    ArrayList<Course> recommendedCourses;
    Intent intent = getIntent();
    TextView titleTextView, organizerTextView, urlTextView, picture_urlTextView, descriptionTextView, priceTextView;
    Button backButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);


        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.course_details);

        Bundle extras =intent.getExtras();

    }



    public void getDetails(){

    }

 /*   public void Display(){
        titleTextView = findViewById(R.id.);
        titleTextView.setText(title);
        organizerTextView = findViewById(R.id.JobTitle);
        organizerTextView.setText(title);
        urlTextView = findViewById(R.id.JobTitle);
        urlTextView.setText(title);
        descriptionTextView = findViewById(R.id.JobTitle);
        descriptionTextView.setText(title);
        priceTextView = findViewById(R.id.);
        priceTextView.setText(price.toString());

    }*/

    public void OnGetRecommendationClick(){

    }


}
