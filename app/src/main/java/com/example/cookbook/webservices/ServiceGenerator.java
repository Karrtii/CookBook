package com.example.cookbook.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static CategoryApi categoryApi;
    private static RecipeTitleApi recipeTitleApi;
    private static RecipeDetailApi recipeDetailApi;

    public static CategoryApi getCategoryApi()
    {
        if(categoryApi == null)
        {
            categoryApi = new Retrofit.Builder().baseUrl("https://recipesapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build().create(CategoryApi.class);
        }
        return categoryApi;
    }

    public static RecipeTitleApi getRecipeTitleApi()
    {
        if(recipeTitleApi == null)
        {
            recipeTitleApi = new Retrofit.Builder().baseUrl("https://recipesapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build().create(RecipeTitleApi.class);
        }
        return recipeTitleApi;
    }

    public static RecipeDetailApi getRecipeDetailApi()
    {
        if(recipeDetailApi == null)
        {
            recipeDetailApi = new Retrofit.Builder().baseUrl("https://recipesapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build().create(RecipeDetailApi.class);
        }
        return recipeDetailApi;
    }
}
