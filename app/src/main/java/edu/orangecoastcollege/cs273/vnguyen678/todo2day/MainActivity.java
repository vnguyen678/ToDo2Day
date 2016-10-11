package edu.orangecoastcollege.cs273.vnguyen678.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for now, delete the old data base
        this.deleteDatabase(DBHelper.DATABASE_TABLE);

        // let manek a DBhelper reference:
        DBHelper db = new DBHelper(this);

        // Let's make a new task and add to database
        db.addTask(new Task(1, "Study for cs273 Midterm", 0));
        db.addTask(new Task(2, "Sleep", 0));
        db.addTask(new Task(3, "Sleep Sleep", 0));
        db.addTask(new Task(4, "More Sleep", 0));
        db.addTask(new Task(5, "Even More Sleep", 0));

        // let get all the task form the database and print them with log.u
        ArrayList<Task> allTask = db.getAllTask();
        //loop throught each task print to log.i
        for(Task sigleTask : allTask)
            Log.i("DATABASE TASK", sigleTask.toString());
    }






}
