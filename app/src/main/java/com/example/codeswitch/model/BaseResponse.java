package com.example.codeswitch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Abstraction of a typical response
 * Usage of strategy pattern
 */
public abstract class BaseResponse {
    @SerializedName("success")
    @Expose
    protected Boolean success;
    @SerializedName("message")
    @Expose
    protected String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract String toString();
}
