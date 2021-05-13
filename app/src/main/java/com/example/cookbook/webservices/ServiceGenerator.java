package com.example.cookbook.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static CategoryApi categoryApi;

    public static CategoryApi getCategoryApi()
    {
        if(categoryApi == null)
        {
            categoryApi = new Retrofit.Builder().baseUrl("https://recipesapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build().create(CategoryApi.class);
        }
        return categoryApi;
    }
}
