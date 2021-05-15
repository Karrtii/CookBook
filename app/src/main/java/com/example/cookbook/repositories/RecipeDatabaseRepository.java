package com.example.cookbook.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cookbook.database.RecipeDAO;
import com.example.cookbook.database.RecipeDatabase;
import com.example.cookbook.model.AddRecipe;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipeDatabaseRepository {

    private static RecipeDatabaseRepository instance;
    private final RecipeDAO recipeDAO;
    private final LiveData<List<AddRecipe>> allRecipes;
    private final ExecutorService executorService;

    public RecipeDatabaseRepository(Application application)
    {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipeDAO = database.recipeDAO();
        allRecipes = recipeDAO.getAllRecipes();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized RecipeDatabaseRepository getInstance(Application application)
    {
        if(instance == null)
        {
            instance = new RecipeDatabaseRepository(application);
        }
        return instance;
    }

    public LiveData<List<AddRecipe>> getAllRecipes()
    {
        return allRecipes;
    }

    public void insert(AddRecipe recipe)
    {
        executorService.execute(() -> recipeDAO.insert(recipe));
    }
}
