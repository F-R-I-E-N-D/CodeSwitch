package com.example.codeswitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codeswitch.ModifiedActivity;
import com.example.codeswitch.R;
import com.example.codeswitch.model.Course;
import com.example.codeswitch.model.Interest;
import com.example.codeswitch.model.Skill;
import com.example.codeswitch.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditProfileActivity extends ModifiedActivity {

    private String gameState;
    private TextView usernameTextView;
    private List<TextView> skillTextView = new ArrayList<>();
    private ConstraintLayout layout;
    private User currentUser;
    private final String TAG = "EditProfile";
//    private Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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
        if(skills.size() == 0) {
            TextView newTextView = new TextView(this);
            newTextView.setText("None");
//            newTextView.setTextColor(#0066ff); // for example
            newTextView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    System.out.println("dhgjgf jfgsfjhsgfsjfgdfjh");
                }
            });
        }
        else {
            for (String s : skills) {
                TextView newTextView = new TextView(this);
                newTextView.setText(s);
//            newTextView.setTextColor(#0066ff); // for example
                newTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("dhgjgf jfgsfjhsgfsjfgdfjh");
                    }
                });
//
                skillTextView.add(newTextView);
            }
            int viewidsize = 0;
            for (TextView tv : skillTextView) {
                tv.setId(viewidsize++); // Views must have IDs in order to add them to chain later.
                layout.addView(tv);
            }
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);
            View previousItem = null;
            for (TextView tv : skillTextView) {
                boolean lastItem = skillTextView.indexOf(tv) == skillTextView.size() - 1;
                if (previousItem == null) {
                    constraintSet.connect(tv.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                } else {
                    constraintSet.connect(tv.getId(), ConstraintSet.LEFT, previousItem.getId(), ConstraintSet.RIGHT);
                    if (lastItem) {
                        constraintSet.connect(tv.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                    }
                }
                previousItem = tv;
            }
//        // [1, 2, 3, 4, 5]

            int[] viewIds = new int[viewidsize];
            int idCount = 0;
            for (TextView i : skillTextView)
                viewIds[idCount] = i.getId();

//        int[] viewIds = ByteUtils.toIntArray(new ArrayList<>(Collections2.transform(skillTextView, View::getId)));
            constraintSet.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIds, null, ConstraintSet.CHAIN_SPREAD);
            constraintSet.applyTo(layout);
        }

    }


}
