package com.kalai.todo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.List;


public class Repository {
    private TodosDao todosDao;
    private LiveData<List<Todo>> todos;

    Repository(Context context){
    RoomDB db=RoomDB.getDataBase(context);
    todosDao=db.todosDao();
    todos=todosDao.getAllTodos();
    }


    void insert(Todo todo){
        new insertAsynctask(todosDao).execute(todo);
    }
    void delete(Todo todo){
        new deleteAsynctask(todosDao).execute(todo);
    }

    void deleteAll(){
        new deleteAllAsynctask(todosDao).execute();
    }


    LiveData<List<Todo>> getAllTodos(){
        return todos;
    }


    private static class insertAsynctask extends AsyncTask<Todo,Void,Void>{
        private WeakReference<TodosDao> notesDaoWeakReference;

        insertAsynctask(TodosDao notesDao){
            notesDaoWeakReference= new WeakReference<>(notesDao);
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            notesDaoWeakReference.get().insert(todos[0]);
            return null;
        }
    }

    private static class deleteAsynctask extends AsyncTask<Todo,Void,Void>{
        private WeakReference<TodosDao> notesDaoWeakReference;

        deleteAsynctask(TodosDao notesDao){
            notesDaoWeakReference= new WeakReference<>(notesDao);
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            notesDaoWeakReference.get().delete(todos[0]);
            return null;
        }
    }

    private static class deleteAllAsynctask extends AsyncTask<Void,Void,Void>{
        private WeakReference<TodosDao> notesDaoWeakReference;

        deleteAllAsynctask(TodosDao notesDao){
            notesDaoWeakReference= new WeakReference<>(notesDao);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            notesDaoWeakReference.get().deleteAll();
            return null;
        }
    }

}
