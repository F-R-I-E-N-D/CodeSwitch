package com.example.codeswitch.model;

import java.util.List;

import com.example.codeswitch.model.BaseObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course extends BaseObject {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("organizer")
    @Expose
    private String organizer;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("date_posted")
    @Expose
    private String datePosted;
    @SerializedName("course_src")
    @Expose
    private String courseSrc;
    @SerializedName("picture_src")
    @Expose
    private String pictureSrc;
    @SerializedName("skills_taught")
    @Expose
    private List<String> skillsTaught = null;
    @SerializedName("url")
    @Expose
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getCourseSrc() {
        return courseSrc;
    }

    public void setCourseSrc(String courseSrc) {
        this.courseSrc = courseSrc;
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }

    public List<String> getSkillsTaught() {
        return skillsTaught;
    }

    public void setSkillsTaught(List<String> skillsTaught) {
        this.skillsTaught = skillsTaught;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", organizer='" + organizer + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", datePosted='" + datePosted + '\'' +
                ", courseSrc='" + courseSrc + '\'' +
                ", pictureSrc='" + pictureSrc + '\'' +
                ", skillsTaught=" + skillsTaught +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}