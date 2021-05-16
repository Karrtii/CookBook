package com.example.cookbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cookbook.model.Favourite;

@Database(entities = {Favourite.class}, version = 1)
public abstract class FavouriteDatabase extends RoomDatabase {

    private static FavouriteDatabase instance;
    public abstract FavouriteDAO favouriteDAO();

    public static synchronized FavouriteDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, FavouriteDatabase.class, "favouriteDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
