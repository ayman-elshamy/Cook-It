package com.example.cookit.api

import com.example.cookit.Categories.CategoryResponse
import com.example.cookit.meals.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallable {

    @GET("api/json/v1/1/categories.php")
    fun getData(): Call<CategoryResponse>

    @GET("api/json/v1/1/filter.php")
    fun getMeals(@Query("c") category: String): Call<MealsResponse>
}
