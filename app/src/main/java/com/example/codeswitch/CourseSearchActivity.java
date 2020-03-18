package com.example.codeswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CourseSearchActivity extends ModifiedActivity implements SearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
    }

    @Override
    public void fetchDisplayItems(String keyword) {

    }

    @Override
    public void displayItems() {

    }
}
