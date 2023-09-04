package com.pouyaa.feature.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pouyaa.model.Category

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel(),
    onCategoryClicked: (String) -> Unit
) {
    when (val state = viewModel.uiState) {
        is CategoriesViewModel.UiState.Loading -> LoadingView()
        is CategoriesViewModel.UiState.Success -> CategoriesList(
            categories = state.data,
            onCategoryClicked = onCategoryClicked
        )
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(64.dp))
    }
}

@Composable
fun CategoriesList(
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
            onClicked(category.id)
        }
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            val painter = rememberAsyncImagePainter(model = category.imageUrl)
            Image(
                painter = painter,
                contentDescription = category.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = category.name,
                modifier = Modifier
                    .background(
                        alpha = 0.9f, brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.secondaryContainer
                            ), endY = 35f
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