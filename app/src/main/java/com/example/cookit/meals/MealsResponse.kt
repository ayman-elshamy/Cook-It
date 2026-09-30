package com.example.cookit.meals

import com.example.cookit.api.Constants.MEALS
import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName(MEALS)
    val meals: List<Meals>? = null
)
