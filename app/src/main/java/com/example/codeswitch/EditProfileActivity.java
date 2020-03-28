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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codeswitch.model.Job;
import com.example.codeswitch.model.Skill;
import com.example.codeswitch.model.User;
import com.example.codeswitch.network.ApiManager;
import com.example.codeswitch.network.CustomCallback;
import com.example.codeswitch.network.Dao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditProfileActivity extends ModifiedActivity {

    private String gameState;
    private TextView usernameTextView;
    private List<String> userUpdatedSkills;
//    private List<TextView> skillTextView = new ArrayList<>();
    private ConstraintLayout layout;
    private User currentUser;
    private final String TAG = "EditProfile";
    private Dao dao;


    //    private Intent thisIntent;
    Button addskill;
    //TextView textview;
    ///dummy data for categories
    String[] SkillGroups = new String[]{
            "Artificial Intelligence",
            "Database",
            "Language",
            "Misc",
            "Mobile",
            "Web Development"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        dao = ApiManager.getInstance().create(Dao.class);
        addskill = (Button)findViewById(R.id.AddSkill);
        getDetails();
//        List<String> userUpdatedSkills = new ArrayList<>();
        userUpdatedSkills = currentUser.getSkills();



        addskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> userTempSkills = new ArrayList<>();

                final AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(EditProfileActivity.this);


                alertdialogbuilder.setTitle("Select A Field ");


                alertdialogbuilder.setItems(SkillGroups, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        final String selectedField = Arrays.asList(SkillGroups).get(which);
                        //pass selectedField to retrieve list of associated skills
                        final List<String> skillsArray = new ArrayList<>();


                        //=============================================================
                        ApiManager.callApi(dao.getSkillList(), new CustomCallback<List<Skill>>() {
                            @Override
                            public void onResponse(List<Skill> response) {
                                if (response != null) {
//                                    int i = 0;

                                    ApiManager.callApi(dao.getSkillsInGroup(selectedField), new CustomCallback<List<Skill>>() {
                                        @Override
                                        public void onResponse(List<Skill> response) {
                                            List<String> skillNames = new ArrayList<>();
                                            for (Skill skill: response) {
                                                skillsArray.add(skill.getName());
                                            }
                                            String[] finalSkillsArray = new String[skillsArray.size()];
                                            finalSkillsArray = skillsArray.toArray(finalSkillsArray);

//                                            filteredSkills(finalSkillsArray); //returns String Array of selected

                                                final AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);

                                                final boolean[] checkedItems = new boolean[finalSkillsArray.length];
                                                Arrays.fill(checkedItems, Boolean.FALSE);
                                                builder.setMultiChoiceItems(finalSkillsArray, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                                    }
                                                });
                                                builder.setCancelable(false);
                                                builder.setTitle("Select Skill To Add");
                                            final String[] finalSkillsArray1 = finalSkillsArray;
                                            builder.setNeutralButton("Back", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        int a = 0;
                                                        while(a < checkedItems.length)
                                                        {
                                                            boolean value = checkedItems[a];

                                                            if(value){
                                                                userTempSkills.add(finalSkillsArray1[a]);
                                                                //store selected skills!! wew!!
                                                            }

                                                            a++;
                                                        }
                                                    alertdialogbuilder.show();
                                                    }
                                                });
                                                AlertDialog dialog = builder.create();

                                                dialog.show();

                                            }


                                    });

//

                                }
                                else {
                                    Log.d("Debug", "Response was null");
                                }
                            }
                        });



                    }
                });
                alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //store selected values!! wew!!
//                        userUpdatedSkills.addAll(userTempSkills);
                        List<String> tempCopy = new ArrayList<>(userTempSkills);
                        tempCopy.removeAll(userUpdatedSkills);
                        userUpdatedSkills.addAll(tempCopy);
                        display();

                    }
                });

                alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        display();
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
                String[] userSkillsArray = new String[userUpdatedSkills.size()];
                userSkillsArray = userUpdatedSkills.toArray(userSkillsArray);


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
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(3);
        menuItem.setChecked(true);

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
        display();

    }
    public void getDetails(){
        currentUser = getUserFromPrefs();

    }

    public void display(){
        usernameTextView = findViewById(R.id.Username);
        usernameTextView.setText(currentUser.getEmail());
        List<String> skills = userUpdatedSkills;

        int i=0;
        androidx.gridlayout.widget.GridLayout sgl = findViewById(R.id.userSkillsGridLayout);
        sgl.setColumnCount(3);
        for (String s: skills) {
            TextView tv = new TextView(this);
            tv.setId(i+1000);
            tv.setText(s);
            tv.setTextSize(20); //set 20sp size of text
            tv.setBackgroundColor(0xFFFDFD96);//set background color
            tv.setPadding(10, 10, 10, 10);



            sgl.addView(tv);

        }

    }


}
