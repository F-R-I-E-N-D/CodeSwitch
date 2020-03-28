package com.example.codeswitch.network;

import android.util.Log;

import com.example.codeswitch.model.AuthResponse;
import com.example.codeswitch.model.BaseObject;
import com.example.codeswitch.model.Job;

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
        ApiManager.callApi(dao.createAccount("adi@example.com", "Adi12345"), new CustomCallback<AuthResponse>() {
            @Override
            public void onResponse(AuthResponse response) {
                if (response != null) {
                    Log.d("Debug", response.toString());
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }

    public static void testGetJobs()
    {
        // Invalid password
        ApiManager.callApi(dao.getJobBySearch("Commonwealth Bank"), new CustomCallback<List<Job>>() {
            @Override
            public void onResponse(List<Job> response) {
                if (response != null) {
                    for (Job job: response)
                    {
                        Log.d("Debug", job.toString());
                    }
                }
                else {
                    Log.d("Debug", "Response was null");
                }
            }
        });
    }
}
