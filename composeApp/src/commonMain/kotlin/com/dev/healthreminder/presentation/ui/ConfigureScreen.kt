package com.dev.healthreminder.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.healthreminder.data.mapper.toConfigureItemModel
import com.dev.healthreminder.data.remote.RemoteDataSource
import com.dev.healthreminder.presentation.ui.components.EmptyContentPlaceholder
import com.dev.healthreminder.presentation.ui.components.LargeText
import com.dev.healthreminder.presentation.ui.components.ReminderCardItemActions
import com.dev.healthreminder.presentation.ui.components.ReminderCardItems
import com.dev.healthreminder.presentation.ui.theming.AppTheme
import com.dev.healthreminder.presentation.ui.theming.ButtonHeight
import com.dev.healthreminder.presentation.ui.theming.LargeSpacing
import com.dev.healthreminder.presentation.ui.theming.Margin
import com.dev.healthreminder.presentation.ui.theming.StrokeWidth
import healthreminder.composeapp.generated.resources.Res
import healthreminder.composeapp.generated.resources.clear_all_reminder
import healthreminder.composeapp.generated.resources.save_changes
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfigureScreen() {
//    val viewModel: ConfigureViewModel = koinViewModel()

//    val reminders by viewModel.reminders.collectAsState()

    val reminders =
        RemoteDataSource().getUserConfigurationResponse.map {
            it.toConfigureItemModel()
        }

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = Margin),
        contentAlignment = Alignment.TopCenter,
    ) {
        when {
            reminders.isEmpty() -> {
                EmptyContentPlaceholder()
            }

            else -> {
                LazyColumn {
                    items(
                        items = reminders,
                        key = { it.reminderId },
                    ) { configureItem ->
                        Spacer(modifier = Modifier.height(LargeSpacing))
                        ReminderCardItems(
                            configureItem = configureItem,
                            actions =
                                ReminderCardItemActions(
                                    onSwitched = { isSelected ->
                                    },
                                    onSettingsClicked = {
                                    },
                                    onIntervalSelected = { millis ->
                                    },
                                ),
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier =
                Modifier
                    .padding(bottom = (Margin + Margin) + ButtonHeight)
                    .fillMaxWidth()
                    .height(ButtonHeight)
                    .align(Alignment.BottomCenter),
        ) {
            Button(
                onClick = {
                },
            ) {
                LargeText(
                    text = stringResource(Res.string.save_changes),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        OutlinedButton(
            onClick = {
            },
            border =
                BorderStroke(
                    width = StrokeWidth,
                    color = MaterialTheme.colorScheme.primary,
                ),
            modifier =
                Modifier
                    .padding(bottom = Margin)
                    .fillMaxWidth()
                    .height(ButtonHeight)
                    .align(Alignment.BottomCenter),
        ) {
            LargeText(
                text = stringResource(Res.string.clear_all_reminder),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
fun ConfigureScreenPreview() {
    AppTheme {
        ConfigureScreen()
    }
}
