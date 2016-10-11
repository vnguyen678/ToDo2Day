package edu.orangecoastcollege.cs273.vnguyen678.todo2day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    private static final String DATABASE_NAME = "ToDo2Day";
    static final String DATABASE_TABLE = "Tasks";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_IS_DONE = "is_done";


    public DBHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_IS_DONE + " INTEGER" + ")";
        database.execSQL (table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    // Create a method to add a brand new task to the database:
    public void addTask(Task newTask)
    {
        // 1 Create a reference to our database:
        SQLiteDatabase db = this.getWritableDatabase();

        // 2 Make a key-value pair for each value you want to insert
        ContentValues values = new ContentValues();
        values.put(KEY_FIELD_ID, newTask.getId());
        values.put(FIELD_DESCRIPTION, newTask.getDescription());
        values.put(FIELD_IS_DONE, newTask.getIsDone());

        // 3 insert the value into our database
        db.insert(DATABASE_TABLE, null, values);

        // 4 close the database
        db.close();

    }

    public ArrayList<Task> getAllTask()
    {
        // 1 Create a reference to our database:
        SQLiteDatabase db = this.getWritableDatabase();
        // 2 make a new empty ArrayList
        ArrayList<Task> allTask = new ArrayList<>();
        // 3 Query the database for all records (all row and all firelds (all colums
        // the return type of a query os a Cursor
        Cursor results = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        // 4 loop throught the results , create Task objects, add to the array list
        if(results.moveToFirst())
        {
            do
            {
                int id = results.getInt(0);
                String description = results.getString(1);
                int isDone = results.getInt(2);
                allTask.add(new Task(id, description, isDone));
            }while(results.moveToNext());
        }

        db.close();
        return allTask;
    }



}
