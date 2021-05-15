package com.example.cookbook.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.repositories.RecipeDatabaseRepository;

import java.util.List;

import retrofit2.http.Url;

public class AddRecipeViewModel extends AndroidViewModel {

    private final RecipeDatabaseRepository recipeRepository;

    public AddRecipeViewModel(Application application)
    {
        super(application);
        recipeRepository = RecipeDatabaseRepository.getInstance(application);
    }



    public void insert(final AddRecipe recipe)
    {
        recipeRepository.insert(recipe);
    }

}
