package com.example.cookit.Categories

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
                if (response.isSuccessful) {
                    categories = response.body()?.categories ?: emptyList()
                }
            }

            override fun onFailure(
                call: Call<CategoryResponse>,
                t: Throwable
            ) {

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
                    onClick = {
                        selectedCategory = category.nameCategory
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rainbowGradient = Brush.sweepGradient(
        colors = listOf(
            Color(0xFFFFD54F), // Yellow
            Color(0xFF81C784), // Green
            Color(0xFF4FC3F7), // Cyan/Blue
            Color(0xFFBA68C8), // Purple
            Color(0xFFE57373), // Pink/Red
            Color(0xFFFFB74D), // Orange
            Color(0xFFFFD54F)  // Back to Yellow to close sweep
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable {}
            .padding(4.dp)
    ) {
        AsyncImage(
            model = category.linkCategory,
            contentDescription = category.nameCategory,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.nameCategory ?: "UNKNOWN",
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Black
        )
    }
}
