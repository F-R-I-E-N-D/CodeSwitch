package com.example.codeswitch;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.TextView;

public class JobDetailsActivity extends ModifiedActivity implements DetailsActivity {

    String gameState;
    Intent intent = getIntent();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }

        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.job_details);

        Bundle extras =intent.getExtras();

        // initialize member TextView so we can manipulate it later
        textView = (TextView) findViewById(R.id.text_view);



    }


    protected void onDestroy();{

    }

    public void getDetails(){

    }

    public void Display(){
        Layout jobName = new Layout("Job Name:",)
    }

    public void OnSaveJobClick(){

    }

    public void OnBackClick(){
        this.finish();
    }
}

