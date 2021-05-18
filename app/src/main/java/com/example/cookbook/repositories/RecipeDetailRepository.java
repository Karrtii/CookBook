package com.example.cookbook.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.model.RecipeDetail;
import com.example.cookbook.model.RecipeDetailResponse;
import com.example.cookbook.webservices.RecipeDetailApi;
import com.example.cookbook.webservices.ServiceGenerator;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailRepository {

    private static RecipeDetailRepository instance;
    private final MutableLiveData<RecipeDetail> searchedRecipeDetail;

    private RecipeDetailRepository()
    {
        searchedRecipeDetail = new MutableLiveData<>();
    }

    public static synchronized RecipeDetailRepository getInstance()
    {
        if (instance == null)
        {
            instance = new RecipeDetailRepository();
        }
        return instance;
    }

    public LiveData<RecipeDetail> getSearchedRecipeDetail()
    {
        return searchedRecipeDetail;
    }

    public void searchForRecipeDetail(String id)
    {
        Log.i("TEST", "I got here");
        RecipeDetailApi recipeDetailApi = ServiceGenerator.getRecipeDetailApi();
        Call<ResponseBody> call = recipeDetailApi.getRecipeDetails(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    try {
                        JSONObject object = new JSONObject(response.body().string());

                        JSONObject recipes = object.getJSONObject("recipe");

                        RecipeDetail recipeDetailResponse = RecipeDetailResponse.fromJson(recipes).getRecipeDetails();

                        searchedRecipeDetail.setValue(recipeDetailResponse);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong in recipe details");
            }
        });
    }
}
