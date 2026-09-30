package com.example.cookit.api

import com.example.cookit.Categories.CategoryResponse
import com.example.cookit.api.Constants.CATEGORIES_ENDPOINT
import com.example.cookit.api.Constants.FILTER_ENDPOINT
import com.example.cookit.meals.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallable {

    @GET(CATEGORIES_ENDPOINT)
    fun getData(): Call<CategoryResponse>

    @GET(FILTER_ENDPOINT)
    fun getMeals(@Query("c") category: String): Call<MealsResponse>
}
