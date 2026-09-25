package com.example.cookit.meals

import com.google.gson.annotations.SerializedName

data class Meals(
    @SerializedName("idMeal")
    val idMeal: String? = null,

    @SerializedName("strMeal")
    val mealName: String? = null,

    @SerializedName("strMealThumb")
    val mealImage: String? = null
)
