package com.example.codeswitch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Schema is still subject to change
public class UserJob extends BaseObject {
    @SerializedName("user")
    @Expose
    private Integer userId;
    @SerializedName("job")
    @Expose
    private Integer jobId;
    @SerializedName("is_saved")
    @Expose
    private Boolean isSaved;
    @SerializedName("is_qualified")
    @Expose
    private Boolean isQualified;
    @SerializedName("has_applied")
    @Expose
    private Boolean hasApplied;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public Boolean getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(Boolean isQualified) {
        this.isQualified = isQualified;
    }

    public Boolean getHasApplied() {
        return hasApplied;
    }

    public void setHasApplied(Boolean hasApplied) {
        this.hasApplied = hasApplied;
    }

    @Override
    public String toString() {
        return "UserJob{" +
                "userId=" + userId +
                ", jobId=" + jobId +
                ", isSaved=" + isSaved +
                ", isQualified=" + isQualified +
                ", hasApplied=" + hasApplied +
                ", id=" + id +
                '}';
    }
}