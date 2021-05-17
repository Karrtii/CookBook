package com.example.cookbook.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.model.CategoryList;
import com.example.cookbook.model.CategoryResponse;
import com.example.cookbook.model.Favourite;
import com.example.cookbook.model.RecipeTitle;
import com.example.cookbook.model.RecipeTitleResponse;
import com.example.cookbook.webservices.RecipeTitleApi;
import com.example.cookbook.webservices.ServiceGenerator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeTitleRepository {

    private static RecipeTitleRepository instance;
    private final MutableLiveData<ArrayList<RecipeTitle>> searchedRecipeTitle;
    private final MutableLiveData<ArrayList<RecipeTitle>> myRecipes;

    private RecipeTitleRepository()
    {
        searchedRecipeTitle = new MutableLiveData<>();
        myRecipes = new MutableLiveData<>();
    }

    public static synchronized RecipeTitleRepository getInstance()
    {
        if (instance == null)
        {
            instance = new RecipeTitleRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<RecipeTitle>> getSearchedRecipeTitles()
    {
        return searchedRecipeTitle;
    }

    public MutableLiveData<ArrayList<RecipeTitle>> getMyRecipes() {
        return myRecipes;
    }

    public void getMyRecipes(ArrayList<Favourite> myFavourites)
    {
        if(myFavourites == null || myFavourites.isEmpty())
        {
            myRecipes.setValue(new ArrayList<>());
            return;
        }

        ArrayList<RecipeTitle> myRecipeTitles = new ArrayList<>();

        for(Favourite f : myFavourites)
        {
            RecipeTitleApi recipeTitleApi = ServiceGenerator.getRecipeTitleApi();

            Call<ResponseBody> call = recipeTitleApi.getRecipe(f.getId());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        JSONObject recipe = json.getJSONObject("recipe");

                        RecipeTitle recipeTitle = RecipeTitleResponse.fromJsonOne(recipe).getRecipeTitles();

                        myRecipeTitles.add(recipeTitle);

                        myRecipes.setValue(myRecipeTitles);

                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("Retrofit", "Something went wrong in favourites");

                }
            });
        }
    }

    public void searchForRecipeTitle(String title)
    {
        RecipeTitleApi recipeTitleApi = ServiceGenerator.getRecipeTitleApi();
        Call<ResponseBody> call = recipeTitleApi.getRecipeTitle(title);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().string());


                        ArrayList<RecipeTitle> recipeTitlesList = new ArrayList<>();
                        JSONArray recipeTitles = object.getJSONArray("recipes");


                        for (int i = 0; i < recipeTitles.length(); i++) {
                            recipeTitlesList.add(RecipeTitleResponse.fromJson(recipeTitles.getJSONObject(i)).getRecipeTitles());
                        }
                        searchedRecipeTitle.setValue(recipeTitlesList);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong in recipe titles");
            }
        });
    }

}
