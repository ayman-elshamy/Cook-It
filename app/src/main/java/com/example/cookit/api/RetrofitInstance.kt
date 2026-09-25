package com.example.cookit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val call: ApiCallable = retrofit.create(ApiCallable::class.java)
}
