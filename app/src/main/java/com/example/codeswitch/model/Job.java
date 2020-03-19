package com.example.codeswitch.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Schema is still subject to change
public class Job extends BaseObject {
    @SerializedName("recommended_courses")
    @Expose
    private List<Integer> recommendedCoursesIds = null;
    @SerializedName("required_skills")
    @Expose
    private List<String> requiredSkills = null;
    @SerializedName("interest_fields")
    @Expose
    private List<String> interestFields = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date_posted")
    @Expose
    private String datePosted;
    @SerializedName("application_src")
    @Expose
    private String applicationSrc;
    @SerializedName("picture_src")
    @Expose
    private String pictureSrc;

    public List<Integer> getRecommendedCoursesIds() {
        return recommendedCoursesIds;
    }

    public void setRecommendedCoursesIds(List<Integer> recommendedCoursesIds) {
        this.recommendedCoursesIds = recommendedCoursesIds;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getInterestFields() {
        return interestFields;
    }

    public void setInterestFields(List<String> interestFields) {
        this.interestFields = interestFields;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getApplicationSrc() {
        return applicationSrc;
    }

    public void setApplicationSrc(String applicationSrc) {
        this.applicationSrc = applicationSrc;
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }

    @Override
    public String toString() {
        return "Job{" +
                "recommendedCoursesIds=" + recommendedCoursesIds +
                ", requiredSkills=" + requiredSkills +
                ", interestFields=" + interestFields +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                ", datePosted='" + datePosted + '\'' +
                ", applicationSrc='" + applicationSrc + '\'' +
                ", pictureSrc='" + pictureSrc + '\'' +
                ", id=" + id +
                '}';
    }
}