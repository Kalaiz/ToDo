package com.kalai.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class NewToDo extends AppCompatActivity {

    RatingBar ratingBar;
    EditText todoEditText;
    public static final String EXTRA_PRIORITY="com.kalai.todo.priority";
    public static final String EXTRA_TODO="com.kalai.todo.todo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);
        ratingBar=findViewById(R.id.priorityRatingBar);
        todoEditText=findViewById(R.id.todoEditText);

    }

    public void add(View view) {
        String todo=todoEditText.getText().toString();
        float rating=ratingBar.getNumStars();
        Intent intent=new Intent();
        if (todo.isEmpty()){
            setResult(RESULT_CANCELED,intent);
        }
        else{
            intent.putExtra(EXTRA_PRIORITY,rating);
            intent.putExtra(EXTRA_TODO,todo);
            setResult(RESULT_OK,intent);
        }
        finish();



    }
}
