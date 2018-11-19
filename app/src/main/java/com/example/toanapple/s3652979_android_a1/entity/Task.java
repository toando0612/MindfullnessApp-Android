package com.example.toanapple.s3652979_android_a1.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Task {
enum Status{
    NEW,DONE;
}
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String name;

    private long duration;

    public Task() {
    }

    public Task(String status, String name, long duration) {
        this.status = status;
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
