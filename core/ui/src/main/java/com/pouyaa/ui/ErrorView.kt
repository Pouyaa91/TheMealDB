package com.pouyaa.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.pouyaa.core.ui.R

@Composable
fun ErrorView(message: String, onRetryClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier,
            text = message.takeIf(String::isNotEmpty)
                ?: stringResource(id = R.string.general_error),
            textAlign = TextAlign.Center
        )
        Button(onClick = onRetryClicked) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}