package com.example.cookbook.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.repositories.RecipeDatabaseRepository;

import java.util.List;

public class YourRecipesViewModel extends AndroidViewModel {

    private final RecipeDatabaseRepository recipeRepository;

    public YourRecipesViewModel(Application application)
    {
        super(application);
        recipeRepository = RecipeDatabaseRepository.getInstance(application);
    }

    public LiveData<List<AddRecipe>> getAllRecipes()
    {
        return recipeRepository.getAllRecipes();
    }
}