package com.example.hobitrac;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Hobby.class}, exportSchema = false, version = 1)
public abstract class HobbyDatabase extends RoomDatabase {
    private static final String DB_NAME = "hobby_db";
    private static  HobbyDatabase instance;

    public static synchronized HobbyDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HobbyDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract HobbyDao HobbyDao();
}