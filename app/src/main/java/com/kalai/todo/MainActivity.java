package com.kalai.todo;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewModel viewModel;
    private ToDoAdapter toDoadapter;
    private RecyclerView toDoRecyclerView;
    private static final int NEW_TODO_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toDoadapter=new ToDoAdapter(this);
        toDoRecyclerView=findViewById(R.id.toDoRecyclerView);
        viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        toDoRecyclerView.setAdapter(toDoadapter);
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),NewToDo.class);
            startActivityForResult(intent,NEW_TODO_REQUEST);
            }
        });


        ((AppViewModel) viewModel).getAllToDos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> todos) {
                toDoadapter.setTodoList(todos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_TODO_REQUEST){
            Integer msgID=null;
            if(resultCode==RESULT_OK){
                msgID=R.string.Toast_msg_new_todo_success;
                assert data != null;
                float priority= data.getFloatExtra(NewToDo.EXTRA_PRIORITY,1.0f);
               String todo=data.getStringExtra(NewToDo.EXTRA_TODO);
                viewModel= ViewModelProviders.of(this).get(AppViewModel.class);
                ((AppViewModel) viewModel).insert(new Todo(todo,0,0,priority));

            }
            else{
                msgID=R.string.Toast_msg_new_todo_fail;
            }
            Toast.makeText(this,getString(msgID),Toast.LENGTH_SHORT).show();
        }

    }
}
