package com.example.cookbook.model;

import org.json.JSONObject;

public class CategoryResponse {

    private String imageUrl, title;

    public CategoryList getCategories()
    {
        return new CategoryList(title, imageUrl);
    }

    public static CategoryResponse fromJson(JSONObject jsonObject)
    {
        CategoryResponse response = new CategoryResponse();

        try
        {
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
