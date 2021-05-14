package com.example.cookbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.model.CategoryList;
import com.example.cookbook.model.RecipeTitle;
import com.example.cookbook.repositories.RecipeTitleRepository;

import java.util.ArrayList;

public class RecipesFromCategoryViewModel extends ViewModel {

    RecipeTitleRepository recipeTitleRepository;

    public RecipesFromCategoryViewModel() {
       recipeTitleRepository = RecipeTitleRepository.getInstance();
    }

    public LiveData<ArrayList<RecipeTitle>> getRecipeTitles()
    {
        return recipeTitleRepository.getSearchedRecipeTitles();
    }

    public void searchForRecipeTitles(String title)
    {
        recipeTitleRepository.searchForRecipeTitle(title);
    }
}
