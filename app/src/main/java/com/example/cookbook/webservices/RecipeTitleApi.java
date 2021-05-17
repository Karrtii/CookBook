package com.example.cookbook.webservices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeTitleApi {

    @GET("api/v2/recipes")
    Call<ResponseBody> getRecipeTitle(@Query("q") String q);

    @GET("api/v2/recipes/{rld}")
    Call<ResponseBody> getRecipe(@Path("rld") String rld);
}
