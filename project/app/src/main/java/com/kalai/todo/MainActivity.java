package com.kalai.todo;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements EditDialogFragmentListener{


    private AppViewModel viewModel;
    private ToDoAdapter toDoadapter;
    private RecyclerView toDoRecyclerView;
    private static final int NEW_TODO_REQUEST = 1;
    public static final String TODO_TAG="com.kalai.todo.MainActivity.TODO";
    public static final String TODO_POSITION="com.kalai.todo.MainActivity.TODO_POSITION";
    public static final String TODO_PRIORITY_TAG="com.kalai.todo.MainActivity.TODO_PRIORITY";




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
        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position=viewHolder.getAdapterPosition();
                Todo todo=toDoadapter.getTodo(position);
                Toast.makeText(getApplicationContext(),"Deleted "+todo.getTodoText(),Toast.LENGTH_SHORT).show();
                viewModel.delete(todo);


            }
        });
        helper.attachToRecyclerView(toDoRecyclerView);

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
        if (id == R.id.action_delete_all) {
            viewModel.deleteAll();
            Toast.makeText(this,"Cleared the database",Toast.LENGTH_SHORT).show();
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

                viewModel.insert(new Todo(todo,0,0,priority));

            }
            else{
                msgID=R.string.Toast_msg_new_todo_fail;
            }
            Toast.makeText(this,getString(msgID),Toast.LENGTH_SHORT).show();
        }

    }


    public static void sendToEditDialogFragment(int position,String todoText, Float priority, Context context){
        EditDialogFragment dialogFragment=new EditDialogFragment();
        Bundle args=new Bundle();
        args.putString(TODO_TAG,todoText);
        args.putFloat(TODO_PRIORITY_TAG,priority);
        args.putInt(TODO_POSITION,position);
        dialogFragment.setArguments(args);
        dialogFragment.show(((MainActivity)context).getSupportFragmentManager(),EditDialogFragment.TAG);
    }
    @Override
    public void receiveEditDialogFragment(int position,String todo, Float priority) {
        viewModel.delete(toDoadapter.getTodo(position));
        viewModel.insert(new Todo(todo,0,0,priority));
    }

    @Override
    public void noUpdate() {
        Toast.makeText(this,"Todo wasnt updated",Toast.LENGTH_SHORT).show();
    }
}
