package com.example.cookbook.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.model.Favourite;
import com.example.cookbook.model.RecipeTitle;
import com.example.cookbook.repositories.FavouriteDatabaseRepository;
import com.example.cookbook.repositories.RecipeTitleRepository;

import java.util.ArrayList;
import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {

    RecipeTitleRepository recipeTitleRepository;
    private final FavouriteDatabaseRepository favouriteDatabaseRepository;

    public FavouritesViewModel(Application application) {
        super(application);
        recipeTitleRepository = RecipeTitleRepository.getInstance();
        favouriteDatabaseRepository = FavouriteDatabaseRepository.getInstance(application);
    }

    public LiveData<ArrayList<RecipeTitle>> getRecipeTitles()
    {
        return recipeTitleRepository.getSearchedRecipeTitles();
    }

    public void searchForRecipeTitles(String title)
    {
        recipeTitleRepository.searchForRecipeTitle(title);
    }

    public LiveData<List<Favourite>> getAllFavouriteIds()
    {
        return favouriteDatabaseRepository.getAllFavouriteIds();
    }

    public void getMyRecipes(ArrayList<Favourite> myFavourites)
    {
        recipeTitleRepository.getMyRecipes(myFavourites);
    }

    public MutableLiveData<ArrayList<RecipeTitle>> getMyRecipes()
    {
        return recipeTitleRepository.getMyRecipes();
    }
}