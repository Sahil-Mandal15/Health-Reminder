package com.dev.healthreminder.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.healthreminder.presentation.ui.theming.EMPTY_CONTENT_STRING

@Composable
fun EmptyContentPlaceholder(
    modifier: Modifier = Modifier
) {
    MediumText(
        text = EMPTY_CONTENT_STRING,
        modifier = modifier
    )
}