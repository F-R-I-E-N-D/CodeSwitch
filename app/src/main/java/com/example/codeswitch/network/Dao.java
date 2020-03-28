package com.example.codeswitch.network;

import com.example.codeswitch.model.Job;
import com.example.codeswitch.model.SavedJob;
import com.example.codeswitch.model.Skill;
import com.example.codeswitch.model.User;
import com.example.codeswitch.model.AuthResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Look at ApiTest for an example of how to use these functions.
 */
public interface Dao {

    //region USERS

    /**
     * Trigger if you want to get a user's details based on their ID.
     * Details include things like skills.
     *
     * @param id user id
     * @return user object returned
     */
    @GET("users/{user_id}")
    Call<User> getUserDetail(@Path("user_id") int id);

    /**
     * Trigger when logging in. Server will run checks.
     *
     * @param email    User inputted email in the login screen
     * @param password User inputted password in the login screen
     * @return Whether or not login was successful. AuthResponse object is returned.
     */
    @FormUrlEncoded
    @POST("users/login")
    Call<AuthResponse> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    /**
     * Trigger when creating account. Server will run checks.
     * Frontend should do the confirm password check.
     *
     * @param email    User inputted email in the register screen
     * @param password User inputted password in the register screen
     * @return Whether or not the account creation was successful. AuthResponse object returned.
     */
    @FormUrlEncoded
    @POST("users/create-account")
    Call<AuthResponse> createAccount(@Field("email") String email,
                                     @Field("password") String password);

    /**
     * Trigger whenever the user wants to update his skills.
     *
     * @param id     Pass in the user's id that you wanna update
     * @param skills Pass in his new set of skills
     * @return Updated user object. REMEMBER to save user again in SharedPrefs so that the local app knows of this change as well
     */
    @FormUrlEncoded
    @PATCH("users/{user_id}")
    Call<User> updateUserSkills(@Path("user_id") int id,
                                @Field("skills") List<String> skills);

    //endregion

    //region SKILLS

    /**
     * Get list of ALL skills in the database.
     *
     * @return List<Skill>
     */
    @GET("skills")
    Call<List<Skill>> getSkillList();


    /**
     * TODO
     * Get list of GROUPS of skills.
     *
     * @param
     * @return
     */
    @GET("skills/{id}")
    Call<Skill> getSkillGroupList();

    /**
     * TODO
     * Get list of skills of a certain group.
     *
     * @return
     */
    @GET("skills/groups/{id}")
    Call<List<Skill>> getSkillsInGroup();
    //endregion

    //region JOBS

    /**
     * Get a list of jobs based on query keyword.
     * @param q What the user types in the search bar.
     * @return List<Job>
     */
    @GET("query_jobs")
    Call<List<Job>> getJobBySearch(@Query("q") String q);

    @GET("jobs/{job_id}")
    Call<Job> getJob(@Path("job_id") int id);

    //endregion

    //region SAVED_JOBS

    /**
     * Get list of ALL jobs that this specific user saved.
     * It should be split by qualified or not based on Tim's algo.
     * @param id user id
     * @return list of saved jobs
     */
    @GET("saved_jobs/user/{user_id}")
    Call<List<SavedJob>> getUserSavedJobs(@Path("user_id") int id);

    /**
     * When you click apply job, we should change is_applied=True.
     * @param id
     * @param is_applied
     * @return
     */
    @FormUrlEncoded
    @PATCH("saved_jobs/{id}")
    Call<User> applyJob(@Path("id") int id, @Field("is_applied") Boolean is_applied);

    /**
     * Add jobs to saved jobs.
     * @param userId
     * @param jobId
     * @return
     */
    @FormUrlEncoded
    @POST("saved_jobs")
    Call<Job> saveJob(@Field("user") int userId, @Field("job") int jobId);

    /**
     * Remove job from saved jobs. I think it can only be done from saved jobs.
     * @param id Saved job id (different from job id)
     * @return
     */
    @DELETE("saved_jobs/{saved_job_id}")
    Call<Job> unsaveJob(@Path("saved_job_id") int id);

    //endregion
}