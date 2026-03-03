package com.dev.healthreminder.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.healthreminder.presentation.ui.theming.EMPTY_CONTENT_STRING

@Composable
fun EmptyContentPlaceholder(modifier: Modifier = Modifier) {
    LargeText(
        text = EMPTY_CONTENT_STRING,
        modifier = modifier,
        color = MaterialTheme.colorScheme.error,
    )
}
