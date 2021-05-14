package com.example.cookbook.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.model.RecipeDetail;
import com.example.cookbook.repositories.RecipeDetailRepository;

public class RecipeDetailViewModel extends ViewModel {

    RecipeDetailRepository recipeDetailRepository;

    public RecipeDetailViewModel()
    {
        recipeDetailRepository = RecipeDetailRepository.getInstance();
    }

    public LiveData<RecipeDetail> getRecipeDetail()
    {
        return recipeDetailRepository.getSearchedRecipeDetail();
    }

    public void searchForRecipeDetail(String id)
    {
        recipeDetailRepository.searchForRecipeDetail(id);
    }
}
