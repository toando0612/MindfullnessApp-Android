package com.example.toanapple.s3652979_android_a1.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.toanapple.s3652979_android_a1.entity.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void saveTask(Task task);

    @Query("SELECT * FROM task ")
    LiveData<List<Task>> getAllTasks();

    @Update
    public void updateTask(Task task);
}
