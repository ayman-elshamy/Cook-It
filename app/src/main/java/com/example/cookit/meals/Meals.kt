package com.example.cookit.meals

import com.example.cookit.api.Constants.ID_MEAL
import com.example.cookit.api.Constants.MEAL_IMAGE
import com.example.cookit.api.Constants.MEAL_NAME
import com.google.gson.annotations.SerializedName

data class Meals(
    @SerializedName(ID_MEAL)
    val idMeal: String? = null,

    @SerializedName(MEAL_NAME)
    val mealName: String? = null,

    @SerializedName(MEAL_IMAGE)
    val mealImage: String? = null
)
