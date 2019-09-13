package com.kalai.todo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;


public class AppViewModel extends AndroidViewModel{

    LiveData<List<Todo>> toDosList;
    Repository repository;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        toDosList=repository.getAllTodos();
    }

    public void insert(Todo todo){
        repository.insert(todo);
    }

    public void deleteAll(){repository.deleteAll();}
    LiveData<List<Todo>> getAllToDos(){
        return toDosList;}

}
