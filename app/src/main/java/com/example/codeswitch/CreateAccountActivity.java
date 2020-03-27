package com.example.codeswitch;

import android.os.Bundle;

import com.example.codeswitch.network.ApiManager;
import com.example.codeswitch.network.Dao;

public class CreateAccountActivity extends ModifiedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ApiTest.testGetCourseDetail();
//        ApiTest.testCreateAccount();
        setContentView(R.layout.activity_new_user);
//        Intent intent = new Intent(this, JobDetailsActivity.class);
//        intent.putExtra("EXIT", false);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }

}
