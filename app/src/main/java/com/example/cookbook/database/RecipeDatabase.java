package com.example.cookbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cookbook.model.AddRecipe;

@Database(entities = {AddRecipe.class}, version = 6)
public abstract class RecipeDatabase extends RoomDatabase {

    private static RecipeDatabase instance;
    public abstract RecipeDAO recipeDAO();

    public static synchronized RecipeDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, RecipeDatabase.class, "recipeDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}