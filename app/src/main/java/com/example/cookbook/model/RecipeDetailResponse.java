package com.example.cookbook.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeDetailResponse {

    private String id, title, imageUrl, publisher, sourceUrl;
    ArrayList<Ingredients> ingredients = new ArrayList<>();

    public RecipeDetail getRecipeDetails()
    {
     return new RecipeDetail(id, title, imageUrl, publisher, sourceUrl, ingredients);
    }

    public static RecipeDetailResponse fromJson(JSONObject jsonObject)
    {
        RecipeDetailResponse response = new RecipeDetailResponse();

        try
        {
            response.id = jsonObject.getString("id");
            response.title = jsonObject.getString("title");
            response.imageUrl = jsonObject.getString("imageUrl");
            response.publisher = jsonObject.getString("publisher");
            response.sourceUrl = jsonObject.getString("sourceUrl");


            JSONArray array = jsonObject.getJSONArray("ingredients");

            for (int i = 0; i < array.length(); i++) {

                response.ingredients.add(new Ingredients(array.getString(i)));
            }

        }catch
        (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return response;
    }
}
