package com.example.codeswitch.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseObject {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("skills")
    @Expose
    private List<String> skills = null;
    @SerializedName("url")
    @Expose
    private String url;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSkillIds() {
        return skills;
    }

    public void setSkillIds(List<String> skillIds) {
        this.skills = skillIds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", skillIds=" + skills +
                ", url='" + url + '\'' +
                '}';
    }
}