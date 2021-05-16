package com.example.cookbook.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cookbook.database.FavouriteDAO;
import com.example.cookbook.database.FavouriteDatabase;
import com.example.cookbook.model.Favourite;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavouriteDatabaseRepository {

    private static FavouriteDatabaseRepository instance;
    private final FavouriteDAO favouriteDAO;
    private final LiveData<List<Favourite>> allFavouriteIds;
    private final ExecutorService executorService;

    public FavouriteDatabaseRepository(Application application)
    {
        FavouriteDatabase database = FavouriteDatabase.getInstance(application);
        favouriteDAO = database.favouriteDAO();
        allFavouriteIds = favouriteDAO.getAllFavouriteIds();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized FavouriteDatabaseRepository getInstance(Application application)
    {
        if(instance == null)
        {
            instance = new FavouriteDatabaseRepository(application);
        }
        return instance;
    }

    public LiveData<List<Favourite>> getAllFavouriteIds()
    {
        return allFavouriteIds;
    }

    public void insert(Favourite favouriteId)
    {
        executorService.execute(() -> favouriteDAO.insert(favouriteId));
    }

}
