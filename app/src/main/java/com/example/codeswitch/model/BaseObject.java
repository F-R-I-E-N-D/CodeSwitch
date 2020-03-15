package com.example.codeswitch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Abstraction of a typical object
 * Usage of strategy pattern
 */
public abstract class BaseObject {
    @SerializedName("id")
    @Expose
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
