package com.kalai.todo;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Todo.class},version=2,exportSchema = false)
public abstract class RoomDB  extends RoomDatabase {
    private static RoomDB instance;
    public abstract TodosDao todosDao();
    public static RoomDB getDataBase(Context context){
        if(instance==null){
            synchronized (RoomDB.class){
                instance= Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,"room_db")
                .fallbackToDestructiveMigration()
                .build();
            }
        }
        return instance;

    }
}
