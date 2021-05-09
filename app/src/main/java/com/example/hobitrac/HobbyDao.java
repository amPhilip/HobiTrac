package com.example.hobitrac;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hobitrac.Hobby;

import java.util.List;

@Dao
public interface HobbyDao {

    @Query("SELECT * FROM hobby ORDER BY _id ASC")
    List<Hobby> getAllHobbies();

    @Insert
    public void addHobby(Hobby hobby);

    @Update
    public void UpdateHobby(Hobby hobby);

    @Delete
    public void deleteHobby(Hobby hobby);

    @Query("SELECT * FROM hobby")
    Cursor getAllHobbiesCursor();
}