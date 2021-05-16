package com.example.cookbook.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.model.Favourite;
import com.example.cookbook.model.RecipeDetail;
import com.example.cookbook.repositories.FavouriteDatabaseRepository;
import com.example.cookbook.repositories.RecipeDetailRepository;

public class RecipeDetailViewModel extends AndroidViewModel {

    RecipeDetailRepository recipeDetailRepository;
    private final FavouriteDatabaseRepository favouriteDatabaseRepository;

    public RecipeDetailViewModel(Application application)
    {
        super(application);
        recipeDetailRepository = RecipeDetailRepository.getInstance();
        favouriteDatabaseRepository = FavouriteDatabaseRepository.getInstance(application);
    }

    public LiveData<RecipeDetail> getRecipeDetail()
    {
        return recipeDetailRepository.getSearchedRecipeDetail();
    }

    public void searchForRecipeDetail(String id)
    {
        recipeDetailRepository.searchForRecipeDetail(id);
    }

    public void insert(final Favourite favouriteId)
    {
        favouriteDatabaseRepository.insert(favouriteId);
    }
}
