package com.pouyaa.themealdb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pouyaa.feature.categories.CategoriesScreen
import com.pouyaa.themealdb.ui.theme.TheMealDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMealDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategoriesScreen(onCategoryClicked = ::onCategoryClicked)
                }
            }
        }
    }

    private fun onCategoryClicked(categoryId: String) {
        Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show()
    }

}