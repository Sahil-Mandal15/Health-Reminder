package com.dev.healthreminder.data.remote

import com.dev.healthreminder.data.pojo.IntervalPojo
import com.dev.healthreminder.data.pojo.UiMetadataPojo
import com.dev.healthreminder.data.pojo.UserConfigurePojo

// TODO: Future scope : Integrating server

class RemoteDataSource {
    private val defaultIntervalList =
        listOf(
            IntervalPojo(
                intervalId = 101,
                intervalPlaceholder = "15 mins",
                intervalValueMillis = 9_00_000,
            ),
            IntervalPojo(
                intervalId = 102,
                intervalPlaceholder = "25 mins",
                intervalValueMillis = 15_00_000,
            ),
            IntervalPojo(
                intervalId = 103,
                intervalPlaceholder = "30 mins",
                intervalValueMillis = 18_00_000,
            ),
            IntervalPojo(
                intervalId = 104,
                intervalPlaceholder = "1 hour",
                intervalValueMillis = 36_00_000,
            ),
        )

    val getUserConfigurationResponse: List<UserConfigurePojo> =
        listOf(
            UserConfigurePojo(
                reminderId = 1001,
                uiMetadata =
                    UiMetadataPojo(
                        displayTitle = "This is a water reminder",
                        description = "This is a water reminder, This is a water reminder, This is a water reminder.",
                        ctaText = "Understood",
                    ),
                defaultIntervalList = defaultIntervalList,
            ),
            UserConfigurePojo(
                reminderId = 1002,
                uiMetadata =
                    UiMetadataPojo(
                        displayTitle = "This is a blink reminder",
                        description = "This is a blink reminder, This is a blink reminder, This is a blink reminder.",
                        ctaText = "Understood",
                    ),
                defaultIntervalList = defaultIntervalList,
            ),
            UserConfigurePojo(
                reminderId = 1003,
                uiMetadata =
                    UiMetadataPojo(
                        displayTitle = "This is a posture correction reminder",
                        description = "This is a posture correction reminder, This is a posture correction reminder, This is a posture correction reminder.",
                        ctaText = "Understood",
                    ),
                defaultIntervalList = defaultIntervalList,
            ),
        )
}
