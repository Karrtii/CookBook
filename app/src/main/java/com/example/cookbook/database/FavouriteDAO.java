package com.example.cookbook.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.model.Favourite;

import java.util.List;

@Dao
public interface FavouriteDAO {

    @Insert
    void insert(Favourite favouriteId);

    @Query("SELECT * FROM favouriteTable")
    LiveData<List<Favourite>> getAllFavouriteIds();
/*
    @Delete
    void delete(Favourite favouriteId);
 */
    @Query("DELETE FROM favouriteTable WHERE id=:favouriteId")
    void delete(String favouriteId);

}
