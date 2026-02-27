package com.dev.healthreminder.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.healthreminder.presentation.ui.theming.ElevationDip
import com.dev.healthreminder.presentation.ui.theming.MediumRound

@Composable
fun ReminderCardItems(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(MediumRound),
        elevation = CardDefaults.cardElevation(
            defaultElevation = ElevationDip
        )
    ) {

    }
}

