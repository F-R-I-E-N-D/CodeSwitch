package com.example.codeswitch.network;

import com.example.codeswitch.model.Course;
import com.example.codeswitch.model.Interest;
import com.example.codeswitch.model.Job;
import com.example.codeswitch.model.Skill;
import com.example.codeswitch.model.User;
import com.example.codeswitch.model.AuthResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Dao {

    //region USERS
    @GET("users/{id}")
    Call<User> getUserDetail(@Path("id") int id);

    @FormUrlEncoded
    @POST("users/login")
    Call<AuthResponse> loginUser(@Field("email") String email,
                                 @Field("password") String password);
    
    @FormUrlEncoded
    @POST("users/create-account")
    Call<AuthResponse> createAccount(@Field("email") String email,
                                     @Field("password") String password);
    
    @FormUrlEncoded
    @PATCH("users/{id}")
    Call<User> updateUserSkills(@Path("id") int id,
                                @Field("skills") List<String> skills);

    @FormUrlEncoded
    @PATCH("users/{id}")
    Call<User> updateUserInterests(@Path("id") int id,
                                   @Field("interests") List<String> skills);
    //endregion

    //region SKILLS
    @GET("skills")
    Call<List<Skill>> getSkillList();
    
    @GET("skills/{id}")
    Call<Skill> getSkillDetail(@Path("id") int id);
    //endregion
    
    //region INTERESTS
    @GET("interests")
    Call<List<Interest>> getInterestList();

    @GET("interests/{id}")
    Call<Interest> getInterestDetail(@Path("id") int id);
    //endregion

    //region COURSES
    @GET("courses")
    Call<List<Course>> getCourseList();

    @GET("courses/{id}")
    Call<Course> getCourseDetail(@Path("id") int id);
    //endregion

    //TODO: Unfinalized schema
    //region JOBS
    @GET("jobs")
    Call<List<Job>> getJobList();

    @GET("jobs" + "/{id}")
    Call<Job> getJobDetail(@Path("id") int id);
    //endregion

    //TODO: Unfinalized schema
    //region USER_JOBS
    @GET("user_jobs")
    Call<List<Job>> getUserJobList();

    @GET("user_jobs" + "/{id}")
    Call<Job> getUserJobDetail(@Path("id") int id);
    //endregion
}
