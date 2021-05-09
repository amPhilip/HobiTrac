package com.example.hobitrac;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Hobby")
public class Hobby {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id")
    public int hobbyId;
    @ColumnInfo(name="hobby_name")
    public String hobby_name;
    @ColumnInfo(name="hobby_time")
    public int hobby_time;

    public int getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(int hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobby_name() {
        return hobby_name;
    }

    public void setHobby_name(String hobby_name) {
        this.hobby_name = hobby_name;
    }

    public int getHobby_time() {
        return hobby_time;
    }

    public void setHobby_time(int hobby_time) {
        this.hobby_time = hobby_time;
    }
}
