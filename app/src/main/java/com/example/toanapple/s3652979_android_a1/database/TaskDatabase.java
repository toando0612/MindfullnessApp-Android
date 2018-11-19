package com.example.toanapple.s3652979_android_a1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.toanapple.s3652979_android_a1.dao.TaskDao;
import com.example.toanapple.s3652979_android_a1.entity.Task;


@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
