package com.kalai.todo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface TodosDao {

    @Insert
    void insert(Todo todo);

    @Query("SELECT * FROM todo_table ORDER BY priority")
    LiveData<List<Todo>> getAllTodos();

    @Delete
    void delete(Todo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

}
