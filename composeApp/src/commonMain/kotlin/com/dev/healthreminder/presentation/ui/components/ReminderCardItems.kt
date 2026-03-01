package com.dev.healthreminder.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.healthreminder.presentation.ui.theming.AppTheme
import com.dev.healthreminder.presentation.ui.theming.ElevationDip
import com.dev.healthreminder.presentation.ui.theming.Gap
import com.dev.healthreminder.presentation.ui.theming.IconSize
import com.dev.healthreminder.presentation.ui.theming.IconSizeSmall
import com.dev.healthreminder.presentation.ui.theming.LargeRound
import com.dev.healthreminder.presentation.ui.theming.MediumRound
import com.dev.healthreminder.presentation.ui.theming.Padding
import com.dev.healthreminder.presentation.ui.theming.StandardCardHeight
import healthreminder.composeapp.generated.resources.Res
import healthreminder.composeapp.generated.resources.ic_blink
import healthreminder.composeapp.generated.resources.ic_settings
import org.jetbrains.compose.resources.painterResource

@Composable
fun ReminderCardItems(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(StandardCardHeight),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.onTertiary
        ),
        shape = RoundedCornerShape(MediumRound),
        elevation = CardDefaults.cardElevation(
            defaultElevation = ElevationDip
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(Padding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_blink),
                contentDescription = "Blink icon",
                modifier = Modifier.size(IconSize)
            )
            Spacer(modifier = Modifier.width(Gap))
            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.Start
            ) {
                MediumText(
                    text = "Blink Reminder",
                    color = MaterialTheme.colorScheme.surface
                )
                SmallText(
                    text = "Active",
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.background(
                        shape = RoundedCornerShape(LargeRound),
                        color = MaterialTheme.colorScheme.primary
                    ).padding(horizontal = Padding)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = true,
                onCheckedChange = {},
                modifier = Modifier.wrapContentSize()
            )
            Spacer(modifier = Modifier.width(Gap))
            Icon(
                painter = painterResource(Res.drawable.ic_settings),
                contentDescription = "Settings",
                modifier = Modifier.size(IconSizeSmall)
            )
        }
    }
}

@Preview
@Composable
fun ReminderCardItemsPreview() {
    AppTheme {
        ReminderCardItems()
    }
}
