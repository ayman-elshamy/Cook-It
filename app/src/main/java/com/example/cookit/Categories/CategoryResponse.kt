package com.example.cookit.Categories

import com.example.cookit.api.Constants.CATEGORIES
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName(CATEGORIES)
    val categories: List<Categories>? = null
)
