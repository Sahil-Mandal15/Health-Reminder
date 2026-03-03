package com.dev.healthreminder.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import com.dev.healthreminder.domain.model.ConfigureItemModel
import com.dev.healthreminder.domain.model.ReminderFeature
import com.dev.healthreminder.presentation.ui.theming.IconSize
import com.dev.healthreminder.presentation.ui.theming.IconSizeSmall
import com.dev.healthreminder.presentation.ui.theming.LargeRound
import com.dev.healthreminder.presentation.ui.theming.MediumRound
import com.dev.healthreminder.presentation.ui.theming.MediumSpacing
import com.dev.healthreminder.presentation.ui.theming.Padding
import com.dev.healthreminder.presentation.ui.theming.StandardCardHeight
import healthreminder.composeapp.generated.resources.Res
import healthreminder.composeapp.generated.resources.active
import healthreminder.composeapp.generated.resources.ic_settings
import healthreminder.composeapp.generated.resources.inactive
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ReminderCardItems(
    modifier: Modifier = Modifier,
    configureItem: ConfigureItemModel,
    actions: ReminderCardItemActions,
) {
    var showIntervalList by remember { mutableStateOf(false) }
    var itemState by remember { mutableStateOf(ItemState.INACTIVE) }

    Column(
        modifier = Modifier.wrapContentSize().animateContentSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier =
                modifier
                    .fillMaxWidth()
                    .height(StandardCardHeight),
            colors =
                CardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContentColor = MaterialTheme.colorScheme.onTertiary,
                ),
            shape = RoundedCornerShape(MediumRound),
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(Padding),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(ReminderFeature.getIconById(configureItem.reminderId)),
                    contentDescription = configureItem.uiMetadata.displayTitle,
                    modifier = Modifier.size(IconSize),
                )
                Spacer(modifier = Modifier.width(MediumSpacing))
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    MediumText(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        text = configureItem.uiMetadata.displayTitle,
                        color = MaterialTheme.colorScheme.surface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    SmallText(
                        text = stringResource(itemState.value),
                        color = MaterialTheme.colorScheme.surface,
                        modifier =
                            Modifier
                                .then(
                                    if (itemState == ItemState.INACTIVE) {
                                        Modifier.alpha(0.5f)
                                    } else {
                                        Modifier.alpha(1f)
                                    },
                                ).background(
                                    shape = RoundedCornerShape(LargeRound),
                                    color = MaterialTheme.colorScheme.primary,
                                ).padding(horizontal = Padding),
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = itemState == ItemState.ACTIVE,
                    onCheckedChange = {
                        itemState = itemState.reverseState()
                        actions.onSwitched.invoke(itemState == ItemState.ACTIVE)
                    },
                    modifier = Modifier.wrapContentSize(),
                )
                Spacer(modifier = Modifier.width(MediumSpacing))
                AnimatedVisibility(
                    visible = itemState == ItemState.ACTIVE,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_settings),
                        contentDescription = "Settings",
                        modifier =
                            Modifier
                                .size(IconSizeSmall)
                                .clickable {
                                    showIntervalList = !showIntervalList
                                    actions.onSettingsClicked.invoke()
                                },
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showIntervalList,
            enter =
                expandVertically(
                    expandFrom = Alignment.Top,
                ),
            exit =
                shrinkVertically(
                    shrinkTowards = Alignment.Top,
                ),
        ) {
            IntervalListView(
                data = configureItem.defaultIntervalList,
                onValueChange = {
                    showIntervalList = false
                    actions.onIntervalSelected.invoke(it)
                },
            )
        }
    }
}

enum class ItemState(
    val value: StringResource,
) {
    ACTIVE(Res.string.active),
    INACTIVE(Res.string.inactive),
    ;

    fun reverseState(): ItemState =
        when (this) {
            ACTIVE -> INACTIVE
            INACTIVE -> ACTIVE
        }
}

data class ReminderCardItemActions(
    val onSwitched: (Boolean) -> Unit,
    val onSettingsClicked: () -> Unit,
    val onIntervalSelected: (millis: Long) -> Unit,
)
