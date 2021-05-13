package com.example.cookbook.webservices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {

    @GET("api/v2/categories")
    Call<ResponseBody> getCategories();
}
