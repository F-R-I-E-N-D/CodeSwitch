package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codeswitch.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditProfileActivity extends ModifiedActivity {

    private String gameState;
    private TextView usernameTextView;
    private List<TextView> skillTextView = new ArrayList<>();
    private ConstraintLayout layout;
    private User currentUser;
    private final String TAG = "EditProfile";

//    private Intent thisIntent;
    Button addskill;
    //TextView textview;
    ///dummy data for categories
    String[] value = new String[]{
            "Android",
            "Php",
            "Blogger",
            "WordPress"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        addskill = (Button)findViewById(R.id.AddSkill);


        addskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(EditProfileActivity.this);


                alertdialogbuilder.setTitle("Select A Field ");

                alertdialogbuilder.setItems(value, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedField = Arrays.asList(value).get(which);
                        //pass selectedField to retrieve list of associated skills


                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });

        Button removeskill;
        removeskill = (Button)findViewById(R.id.RemoveSkill);
        removeskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] userSkillsArray = new String[currentUser.getSkills().size()];
                userSkillsArray = currentUser.getSkills().toArray(userSkillsArray);

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(EditProfileActivity.this);


                alertdialogbuilder.setTitle("Select Skill To Remove");

                alertdialogbuilder.setItems(userSkillsArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedSkill = currentUser.getSkills().get(which);
                        //remove selectedSkill from user's set of skills
                        //confirmation?


                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.ic_job_search:
                        Intent intent_toJS = new Intent(EditProfileActivity.this, JobSearchActivity.class);
                        startActivity(intent_toJS);
                        break;
                    case R.id.ic_course_search:
                        Intent intent_toCS = new Intent(EditProfileActivity.this, CourseSearchActivity.class);
                        startActivity(intent_toCS);
                        break;
                    case R.id.ic_saved_jobs:
                        Intent intent_toSJ = new Intent(EditProfileActivity.this, SavedJobsActivity.class);
                        startActivity(intent_toSJ);
                        break;
                    case R.id.ic_profile:
                        //already here
                        break;
                }

                return false;
            }
        });
        layout = (ConstraintLayout)findViewById(R.id.relLayoutMiddle);
        getDetails();
        display();

    }
    public void getDetails(){
        currentUser = getUserFromPrefs();
    }

    public void display(){
        usernameTextView = findViewById(R.id.Username);
        usernameTextView.setText(currentUser.getEmail());
        List<String> skills = currentUser.getSkills();
//        List<String> interests = currentUser.getInterests();

        int i=0;
        androidx.gridlayout.widget.GridLayout sgl = findViewById(R.id.userSkillsGridLayout);
        sgl.setColumnCount(3);
        for (String s: skills) {
            TextView tv = new TextView(this);
            tv.setId(i+1000);
//            btn.setTag(requiredSkill.getName());
//            btn.setText(requiredSkill.getName());
            tv.setText(s);
            tv.setTextSize(20); //set 20sp size of text
            tv.setBackgroundColor(0xFFFDFD96);//set background color
            tv.setPadding(10, 10, 10, 10);


//            btn.setLayoutParams();

            sgl.addView(tv);
//            btn.setOnClickListener(new View.OnClickListener() {
//            });
//            i++;
        }

    }


}
