package com.example.cookbook.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.repositories.RecipeDatabaseRepository;

import java.util.List;

public class ViewAddedRecipeViewModel extends AndroidViewModel {

    private final RecipeDatabaseRepository recipeRepository;

    public ViewAddedRecipeViewModel(Application application)
    {
        super(application);
        recipeRepository = RecipeDatabaseRepository.getInstance(application);
    }

    public LiveData<List<AddRecipe>> getAllRecipes()
    {
        return recipeRepository.getAllRecipes();
    }
}
