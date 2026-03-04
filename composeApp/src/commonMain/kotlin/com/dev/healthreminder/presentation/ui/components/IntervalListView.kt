package com.dev.healthreminder.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.dev.healthreminder.domain.model.IntervalModel
import com.dev.healthreminder.presentation.ui.theming.FontSmall
import com.dev.healthreminder.presentation.ui.theming.MediumRound
import com.dev.healthreminder.presentation.ui.theming.Padding
import com.dev.healthreminder.presentation.ui.theming.SmallRound
import com.dev.healthreminder.presentation.ui.theming.SmallSpacing
import com.dev.healthreminder.presentation.ui.theming.StrokeWidth
import com.dev.healthreminder.presentation.ui.theming.TextBoundHeight

@Composable
fun IntervalListView(
    modifier: Modifier = Modifier,
    data: List<IntervalModel>,
    state: LazyListState = rememberLazyListState(),
    onValueChange: (Long) -> Unit,
) {
    LazyRow(
        modifier =
            modifier
                .wrapContentWidth()
                .background(
                    shape = RoundedCornerShape(SmallRound),
                    color = Color.Transparent,
                ).padding(vertical = Padding),
        state = state,
    ) {
        items(items = data, key = { it.intervalId }) { value ->
            Spacer(modifier = Modifier.width(SmallSpacing))
            IntervalView(value.intervalPlaceholder, value.intervalValueMillis) {
                onValueChange.invoke(it)
            }
        }
        item {
            Spacer(modifier = Modifier.width(SmallSpacing))
            CustomIntervalView {
                onValueChange.invoke(it)
            }
            Spacer(modifier = Modifier.width(SmallSpacing))
        }
    }
}

@Composable
fun IntervalView(
    value: String,
    millis: Long,
    onSelected: (Long) -> Unit,
) {
    SmallText(
        text = value,
        color = MaterialTheme.colorScheme.primary,
        modifier =
            Modifier
                .border(
                    width = StrokeWidth,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(MediumRound),
                ).clickable {
                    onSelected.invoke(millis)
                }.height(TextBoundHeight)
                .padding(horizontal = Padding),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun CustomIntervalView(input: (Long) -> Unit) {
    var userInput by remember { mutableStateOf("") }
    BasicTextField(
        value = userInput,
        onValueChange = { input.invoke(it.toLong() * 1_000) },
        singleLine = true,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        textStyle =
            TextStyle(
                fontSize = FontSmall,
                color = MaterialTheme.colorScheme.primary,
            ),
        modifier =
            Modifier
                .wrapContentHeight()
                .border(
                    width = StrokeWidth,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(MediumRound),
                ).height(TextBoundHeight)
                .padding(horizontal = Padding),
    )
}
