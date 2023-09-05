package com.pouyaa.themealdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pouyaa.feature.categories.navigation.categoriesNavigationRoute
import com.pouyaa.themealdb.navigation.TheMealDbNavHost
import com.pouyaa.themealdb.ui.theme.TheMealDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TheMealDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheMealDbNavHost(
                        navController = navController,
                        startDestination = categoriesNavigationRoute,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}