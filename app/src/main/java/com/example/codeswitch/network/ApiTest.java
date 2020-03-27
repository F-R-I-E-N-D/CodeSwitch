package com.example.codeswitch.network;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;

import com.example.codeswitch.JobSearchActivity;
import com.example.codeswitch.MainActivity;
import com.example.codeswitch.model.BaseObject;
import com.example.codeswitch.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Just for me to test that the API integration works
 * Call ApiTest.function_name() in MainActivity.onCreate() to test it out
 */
public class ApiTest {
    private static Dao dao = ApiManager.getInstance().create(Dao.class);

    /**
     * Testing updateUserSkills(userId, newSkillsList)
     * It should be around the same as updateUserInterests
     * [response] -- User object with updated Skills, corresponding to the input parameters
     */
    public static void testUpdateUserSkills() {
        List<String> updatedSkills = new ArrayList<>();
        updatedSkills.add("SQL");
        updatedSkills.add("Java");

        ApiManager.callApi(dao.updateUserSkills(8, updatedSkills), new CustomCallback<BaseObject>() {
            @Override
            public void onResponse(BaseObject response) {
                if (response != null) {
                    Log.d("Debug", response.toString());
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }

    /**
     * Testing createAccount(inputEmail, inputPassword)
     * The frontend should do the password & confirm password check.
     * [response] -- AuthResponse object
     */
    public static void testCreateAccount() {
        // Invalid password
        ApiManager.callApi(dao.createAccount("adi@example.com", "Adi12345"), new CustomCallback<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                if (response != null) {
                    Log.d("Debug", response.toString());
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }

    /**
     * Testing getCourseList()
     * It should be around the same as the rest of the getXList() functions
     * [response] -- A list of all the courses in the database
     */
    public static void testGetCourseList() {
        ApiManager.callApi(dao.getCourseList(), new CustomCallback<List<BaseObject>>() {
            @Override
            public void onResponse(List<BaseObject> response) {
                if (response != null) {
                    for (BaseObject obj : response) {
                        Log.d("Debug", obj.toString());
                    }
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }

    /**
     * Testing getCourseDetail(courseId)
     * It should be around the same as the rest of the getXDetail(id) functions
     * [response] -- Course object corresponding to courseId
     */
    public static void testGetCourseDetail() {
        // id = 2
        ApiManager.callApi(dao.getCourseDetail(2), new CustomCallback<List<BaseObject>>() {
            @Override
            public void onResponse(List<BaseObject> response) {
                if (response != null) {
                    Log.d("Debug", response.toString());
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }
}
