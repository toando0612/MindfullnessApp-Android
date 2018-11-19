package com.example.toanapple.s3652979_android_a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toanapple.s3652979_android_a1.entity.Task;
import com.example.toanapple.s3652979_android_a1.repository.TaskRepository;

public class AddActivity extends AppCompatActivity {
    TextView name,duration;
    TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        taskRepository = new TaskRepository(getApplicationContext());

        name = findViewById(R.id.name);
        duration = findViewById(R.id.duration);
    }

    public void onConFirmClick(View view) {
        Task task = new Task("NEW",name.getText().toString(),Long.valueOf(String.valueOf(duration.getText())));
        taskRepository.saveTask(task);
        finish();
    }
}
