package com.example.toanapple.s3652979_android_a1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toanapple.s3652979_android_a1.entity.Task;
import com.example.toanapple.s3652979_android_a1.repository.TaskRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

public class ListActivity extends AppCompatActivity {
    TaskRepository taskRepository;
    String taskname;
    long taskduration;
    private List<Task> listTask = new ArrayList<Task>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        readTask();



    }


    @Override
    protected void onResume() {
        super.onResume();
        readTask();
    }

    public void onAddClick(View view) {
        Intent intent = new Intent(ListActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void readTask(){
        taskRepository = new TaskRepository(getApplicationContext());
        taskRepository.getAllTasks().observe(this, new android.arch.lifecycle.Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                listTask = tasks;

//                for (Task task : tasks
//                        ) {
//                    listTask.add(task);
//
//                }
                ArrayAdapter adapter = new ArrayAdapter(ListActivity.this, android.R.layout.simple_list_item_2, android.R.id.text1, listTask) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view=  super.getView(position, convertView, parent);
                        TextView field1 = view.findViewById(android.R.id.text1);
                        TextView field2 = view.findViewById(android.R.id.text2);

                        field1.setText(listTask.get(position).getName());
                        field2.setText("Duration: "+ String.valueOf(listTask.get(position).getDuration())+ " minutes\n"+"|"+listTask.get(position).getStatus()+"|");

                        return view;
                    }
                };
                ListView listView= findViewById(R.id.listview);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ListActivity.this, "You have clicked "+item, Toast.LENGTH_SHORT).show();
                        Task edit =  listTask.get(position);
                        String string = edit.getStatus();
                        if (string.equals("NEW")) {
                            edit.setStatus("DONE");
                        }else {
                            edit.setStatus("NEW");
                        }

                        taskRepository = new TaskRepository(getApplicationContext());
                        taskRepository.updateTask(edit);
                        recreate();
                        Toast.makeText(ListActivity.this,"You have changed the Status",Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }
}
