package com.example.cookbook.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favouriteTable")
public class Favourite {

    @NonNull
    @PrimaryKey
    private String id;

    public Favourite(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
