package com.example.ps.mvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CreatedUser {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("createdAt")
    @Expose
    private Date createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
