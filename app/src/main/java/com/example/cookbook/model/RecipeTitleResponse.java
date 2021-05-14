package com.example.cookbook.model;

import org.json.JSONObject;

public class RecipeTitleResponse {
    private String id;
    private String title, imageUrl;

    public RecipeTitle getRecipeTitles()
    {
        return new RecipeTitle(id, title, imageUrl);
    }

    public static RecipeTitleResponse fromJson(JSONObject jsonObject)
    {
        RecipeTitleResponse response = new RecipeTitleResponse();

        try
        {
            response.id = jsonObject.getString("id");
            response.title = jsonObject.getString("title");
            response.imageUrl = jsonObject.getString("imageUrl");
        }catch
        (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return response;
    }
}
