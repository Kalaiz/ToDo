package com.kalai.todo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.toDoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Todo> todoList;



   ToDoAdapter(Context context){
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public toDoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view=layoutInflater.inflate(R.layout.item_note,viewGroup,false);
        return new toDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull toDoViewHolder toDoViewHolder, int i) {
       Todo todo=todoList.get(i);
    String todoText =todo.getTodoText();
    float priority=todo.getPriority();
    if(priority>=3.5){
        toDoViewHolder.titleTextView.setBackgroundColor(Color.rgb(248,110,110));
    }
    else if(priority<=2){
        toDoViewHolder.titleTextView.setBackgroundColor(Color.rgb(173,245,103));
    }
    else{
        toDoViewHolder.titleTextView.setBackgroundColor(Color.rgb(33,109,223));
    }
        toDoViewHolder.titleTextView.setText(todoText);
    }

    @Override
    public int getItemCount() {
       if(todoList!=null)
        return todoList.size();
       return 0;
    }
    public Todo getToDO(int position){
       return todoList.get(position);
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    class toDoViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private toDoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.titleTextView);




        }
    }
}
