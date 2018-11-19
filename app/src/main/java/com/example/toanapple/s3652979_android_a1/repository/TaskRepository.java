package com.example.toanapple.s3652979_android_a1.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.toanapple.s3652979_android_a1.database.TaskDatabase;
import com.example.toanapple.s3652979_android_a1.entity.Task;

import java.util.List;

public class TaskRepository {
    private TaskDatabase taskDatabase;

    public TaskRepository(Context context) {
        taskDatabase = Room.databaseBuilder(context, TaskDatabase.class, "tasks").allowMainThreadQueries().build();

    }

    public void saveTask(Task task){taskDatabase.taskDao().saveTask(task);}

    public LiveData<List<Task>> getAllTasks(){
        return taskDatabase.taskDao().getAllTasks();
    }

    public void updateTask(final Task task){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                taskDatabase.taskDao().updateTask(task);
                return null;
            }
        }.execute();
    }
}
