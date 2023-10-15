package com.pouyaa.meals

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pouyaa.model.Meal
import com.pouyaa.ui.ErrorView
import com.pouyaa.ui.LoadingView
import com.pouyaa.ui.ShimmerEffectBrush

@Composable
internal fun MealsRoute(
    viewModel: MealsViewModel = hiltViewModel(),
    onMealClicked: (String) -> Unit
) {
    MealsScreen(
        state = viewModel.uiState,
        onMealClicked = onMealClicked,
        onRetryClicked = viewModel::onRetryClicked
    )
}

@Composable
internal fun MealsScreen(
    state: MealsViewModel.UiState,
    onMealClicked: (String) -> Unit,
    onRetryClicked: () -> Unit
) {
    when (state) {
        is MealsViewModel.UiState.Loading -> LoadingView()
        is MealsViewModel.UiState.Success -> MealsList(
            meals = state.data,
            onMealClicked = onMealClicked
        )

        is MealsViewModel.UiState.Failed -> ErrorView(
            message = state.message,
            onRetryClicked = onRetryClicked
        )
    }
}

@Composable
private fun MealsList(
    modifier: Modifier = Modifier,
    meals: List<Meal>,
    onMealClicked: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(meals) { meal ->
            MealView(meal = meal, onClicked = onMealClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MealView(
    modifier: Modifier = Modifier,
    meal: Meal,
    onClicked: (String) -> Unit
) {
    Surface(
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.onSurface),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(8.dp),
        onClick = {
            onClicked(meal.id)
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            MealImageView(mealName = meal.name, imageUrl = meal.imageUrl)

            Text(
                text = meal.name,
                modifier = Modifier

                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun MealImageView(
    modifier: Modifier = Modifier,
    mealName: String,
    imageUrl: String
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(200)
            .build()
    )

    val isLoading = painter.state is AsyncImagePainter.State.Loading
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isLoading) 1f else 0f,
        label = "Shimmer alpha",
        animationSpec = tween(200)
    )

    Image(
        painter = painter,
        contentDescription = mealName,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(64.dp)
            .background(brush = ShimmerEffectBrush(), alpha = backgroundAlpha)
    )
}