package com.pouyaa.feature.categories

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pouyaa.model.Category
import com.pouyaa.ui.ErrorView
import com.pouyaa.ui.LoadingView
import com.pouyaa.ui.ShimmerEffectBrush

@Composable
internal fun CategoriesRoute(
    viewModel: CategoriesViewModel = hiltViewModel(),
    onCategoryClicked: (String) -> Unit
) {
    CategoriesScreen(
        state = viewModel.uiState,
        onCategoryClicked = onCategoryClicked,
        onRetryClicked = viewModel::onRetryClicked
    )
}

@Composable
internal fun CategoriesScreen(
    state: CategoriesViewModel.UiState,
    onCategoryClicked: (String) -> Unit,
    onRetryClicked: () -> Unit
) {
    when (state) {
        is CategoriesViewModel.UiState.Loading -> LoadingView()
        is CategoriesViewModel.UiState.Success -> CategoriesList(
            categories = state.data,
            onCategoryClicked = onCategoryClicked
        )

        is CategoriesViewModel.UiState.Failed -> ErrorView(
            message = state.message,
            onRetryClicked = onRetryClicked
        )
    }
}

@Composable
private fun CategoriesList(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryClicked: (String) -> Unit
) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
        items(categories) { category ->
            CategoryView(category = category, onClicked = onCategoryClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryView(
    modifier: Modifier = Modifier,
    category: Category,
    onClicked: (String) -> Unit
) {
    Surface(
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.onSurface),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(8.dp)
            .size(128.dp),
        onClick = {
            onClicked(category.name)
        }
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {

            CategoryImageView(categoryName = category.name, imageUrl = category.imageUrl)

            Text(
                text = category.name,
                modifier = Modifier
                    .background(
                        alpha = 0.9f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.secondaryContainer
                            ),
                            endY = 35f
                        )
                    )
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CategoryImageView(
    modifier: Modifier = Modifier,
    categoryName: String,
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
        contentDescription = categoryName,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .background(brush = ShimmerEffectBrush(), alpha = backgroundAlpha)
            .padding(16.dp)
    )
}