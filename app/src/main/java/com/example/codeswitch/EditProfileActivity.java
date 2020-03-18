package com.example.codeswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.codeswitch.ModifiedActivity;
import com.example.codeswitch.R;

public class EditProfileActivity extends ModifiedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}
