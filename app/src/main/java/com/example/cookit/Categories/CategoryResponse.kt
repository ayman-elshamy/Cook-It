package com.example.cookit.Categories

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<Categories>? = null
)
