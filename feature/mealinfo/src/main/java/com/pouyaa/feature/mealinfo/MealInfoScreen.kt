package com.pouyaa.feature.mealinfo

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pouyaa.model.Ingredient
import com.pouyaa.model.Meal
import com.pouyaa.ui.ErrorView
import com.pouyaa.ui.LoadingView
import com.pouyaa.ui.ShimmerEffectBrush

@Composable
internal fun MealInfoRoute(
    viewModel: MealInfoViewModel = hiltViewModel()
) {
    MealInfoScreen(
        state = viewModel.uiState,
        onRetryClicked = viewModel::onRetryClicked
    )
}

@Composable
internal fun MealInfoScreen(
    state: MealInfoViewModel.UiState,
    onRetryClicked: () -> Unit
) {
    when (state) {
        is MealInfoViewModel.UiState.Loading -> LoadingView()
        is MealInfoViewModel.UiState.Success -> MealInfo(meal = state.meal)
        is MealInfoViewModel.UiState.Failed -> ErrorView(
            message = state.message,
            onRetryClicked = onRetryClicked
        )
    }
}

@Composable
private fun MealInfo(
    modifier: Modifier = Modifier,
    meal: Meal
) {
    val scrollState = rememberScrollState()
    Log.i("instruction", meal.instructions)
    Column(modifier = modifier.verticalScroll(scrollState)) {
        MealImageView(mealName = meal.name, imageUrl = meal.imageUrl)
        IngredientsView(ingredients = meal.ingredients)
        InstructionsView(instructions = meal.instructions)
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

    Image(
        painter = painter,
        contentDescription = mealName,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(16 / 9f)
            .run {
                if (painter.state is AsyncImagePainter.State.Loading) {
                    background(brush = ShimmerEffectBrush())
                } else {
                    this
                }
            }
    )
}

@Composable
private fun IngredientsView(modifier: Modifier = Modifier, ingredients: List<Ingredient>) {
    ExpandableSection(modifier = modifier, title = stringResource(id = R.string.ingredients)) {
        Column(modifier = Modifier.padding(8.dp)) {
            ingredients.forEach {
                Text(
                    text = "${it.name} : ${it.portion}",
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Composable
private fun InstructionsView(modifier: Modifier = Modifier, instructions: String) {
    ExpandableSection(modifier = modifier, title = stringResource(id = R.string.how_to_prepare)) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = instructions,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun ExpandableSection(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .clickable { isExpanded = !isExpanded }
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
    ) {
        ExpandableSectionTitle(isExpanded = isExpanded, title = title)

        AnimatedVisibility(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth(),
            visible = isExpanded
        ) {
            content()
        }
    }
}

@Composable
fun ExpandableSectionTitle(modifier: Modifier = Modifier, isExpanded: Boolean, title: String) {
    val icon = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
    Row(modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(32.dp),
            imageVector = icon,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimaryContainer),
            contentDescription = stringResource(id = R.string.expand_or_collapse)
        )
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
    }
}