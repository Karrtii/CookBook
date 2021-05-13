package com.example.cookbook.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.model.CategoryList;
import com.example.cookbook.model.CategoryResponse;
import com.example.cookbook.webservices.CategoryApi;
import com.example.cookbook.webservices.ServiceGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {

    private static CategoryRepository instance;
    private final MutableLiveData<ArrayList<CategoryList>> searchedCategories;

    public CategoryRepository() {
        searchedCategories = new MutableLiveData<>();
        searchForCategories();
    }

    public static synchronized CategoryRepository getInstance()
    {
        if (instance == null)
        {
            instance = new CategoryRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<CategoryList>> getSearchedCategories()
    {
        return searchedCategories;
    }

    public void searchForCategories()
    {
        CategoryApi categoryApi = ServiceGenerator.getCategoryApi();
        Call<ResponseBody> call = categoryApi.getCategories();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    try {
                        JSONObject object = new JSONObject(response.body().string());


                        ArrayList<CategoryList> categoryLists = new ArrayList<>();
                        JSONArray categories = object.getJSONArray("categories");


                        for (int i = 0; i < categories.length(); i++) {
                            categoryLists.add(CategoryResponse.fromJson(categories.getJSONObject(i)).getCategories());
                        }


                        searchedCategories.setValue(categoryLists);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    /*

                    ArrayList<CategoryResponse> responses = new ArrayList<>(response.body());

                    for (CategoryResponse category : responses) {
                        categoryLists.add(category.getCategories());
                    }

                    */
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong");
            }
        });
    }
}
