package com.example.cookbook.webservices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeDetailApi {

    @GET("api/v2/recipes/{rld}")
    Call<ResponseBody> getRecipeDetails(@Path("rld") String rld);
}
