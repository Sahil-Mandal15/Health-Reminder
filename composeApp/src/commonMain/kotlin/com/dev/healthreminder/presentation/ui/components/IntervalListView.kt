package com.dev.healthreminder.presentation.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dev.healthreminder.presentation.ui.theming.AppTheme

@Composable
fun IntervalListView() {
    LazyRow {
    }
}

@Composable
@Preview
fun IntervalListViewPreview() {
    AppTheme {
        IntervalListView()
    }
}
