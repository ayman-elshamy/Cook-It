package com.example.cookit.meals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cookit.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MealsScreen(category: String) {
    var meals by remember { mutableStateOf<List<Meals>>(emptyList()) }

    LaunchedEffect(category) {
        RetrofitInstance.call.getMeals(category).enqueue(object : Callback<MealsResponse> {
            override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>) {
                if (response.isSuccessful) {
                    meals = response.body()?.meals ?: emptyList()
                }
            }

            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {

            }
        })
    }

    MealUi(meals)
}

@Composable
fun MealUi(mealsItem: List<Meals>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(mealsItem) { meal ->
            MealItem(meal = meal)
        }
    }
}

@Composable
fun MealItem(meal: Meals) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE7E5EA))
            .padding(12.dp)
    ) {
        AsyncImage(
            model = meal.mealImage,
            contentDescription = meal.mealName,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = meal.mealName ?: "UNKNOWN",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
