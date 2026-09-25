package com.example.cookit.meals

import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meals>? = null
)
