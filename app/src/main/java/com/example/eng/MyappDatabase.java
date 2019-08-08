package com.example.eng;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version=1)
public abstract class MyappDatabase extends RoomDatabase {

    public abstract MyDao myDao();
}
