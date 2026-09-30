package com.example.cookit.Categories

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cookit.api.RetrofitInstance
import com.example.cookit.meals.MealsScreen
import com.example.cookit.ui.theme.GRADIENT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier
) {

    var categories by remember {
        mutableStateOf<List<Categories>>(emptyList())
    }

    var selectedCategory by remember {
        mutableStateOf<String?>("")
    }

    LaunchedEffect(Unit) {
        RetrofitInstance.call.getData().enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                Log.d("MEALS", "Response = ${response.code()}")

                if (response.isSuccessful) {
                    categories = response.body()?.categories ?: emptyList()
                }
            }

            override fun onFailure(
                call: Call<CategoryResponse>,
                t: Throwable
            ) {
                Log.e("MEALS", "Error = ${t.message}")
            }
        })
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    isSelected = category.nameCategory == selectedCategory,
                    onClick = {
                        selectedCategory = if (selectedCategory == category.nameCategory) {
                            null
                        } else {
                            category.nameCategory
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedCategory.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Select a category to show available meals",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            MealsScreen(category = selectedCategory!!)
        }
    }
}

@Composable
fun CategoryItem(
    category: Categories,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rainbowGradient = Brush.sweepGradient(
        colors = GRADIENT
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        AsyncImage(
            model = category.linkCategory,
            contentDescription = category.nameCategory,
            modifier = Modifier
                .size(80.dp)
                .border(width = 3.dp, brush = rainbowGradient, shape = CircleShape)
                .padding(4.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.nameCategory ?: "UNKNOWN",
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color.Black
        )
    }
}
