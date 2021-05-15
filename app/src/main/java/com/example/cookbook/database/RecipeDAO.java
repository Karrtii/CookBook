package com.example.cookbook.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cookbook.model.AddRecipe;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    void insert(AddRecipe recipe);

    @Query("SELECT * FROM recipeTable")
    LiveData<List<AddRecipe>> getAllRecipes();

    @Delete
    void delete(AddRecipe recipe);

}
