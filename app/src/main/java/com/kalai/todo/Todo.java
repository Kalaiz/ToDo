package com.kalai.todo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "todo_table")
public class Todo {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "todoText")
    private String todoText;
    private long timeStamp;
    private long  timeLeft;
    private float priority;

    Todo(@NonNull String todoText, long timeLeft, long timeStamp, float priority){
        this.todoText = todoText;
        this.timeLeft=timeLeft;
        this.timeStamp=timeStamp;
        this.priority=priority;
    }

    public  long getTimeStamp() {
        return timeStamp;
    }


    public long getTimeLeft() {
        return timeLeft;
    }


    public String getTodoText() {
        return todoText;
    }


    public float getPriority() {
        return priority;
    }


}
