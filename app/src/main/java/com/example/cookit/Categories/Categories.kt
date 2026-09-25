package com.example.cookit.Categories

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("idCategory")
    val id: String? = null,

    @SerializedName("strCategory")
    val nameCategory: String? = null,

    @SerializedName("strCategoryThumb")
    val linkCategory: String? = null,

    @SerializedName("strCategoryDescription")
    val descriptionCategory: String? = null
)
