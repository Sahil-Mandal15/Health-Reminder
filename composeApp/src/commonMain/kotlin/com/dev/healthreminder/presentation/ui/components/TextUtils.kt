package com.dev.healthreminder.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.dev.healthreminder.presentation.ui.theming.FontLarge
import com.dev.healthreminder.presentation.ui.theming.FontMedium
import com.dev.healthreminder.presentation.ui.theming.FontSmall
import com.dev.healthreminder.presentation.ui.theming.FontSmallest

@Composable
fun LargeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = FontLarge,
        fontWeight = fontWeight,
    )
}

@Composable
fun MediumText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = FontMedium,
        fontWeight = fontWeight,
    )
}

@Composable
fun SmallText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = FontSmall,
        fontWeight = fontWeight,
    )
}

@Composable
fun SmallestText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = FontSmallest,
        fontWeight = fontWeight,
    )
}
