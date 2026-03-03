package com.dev.healthreminder.data.mapper

import com.dev.healthreminder.data.pojo.IntervalPojo
import com.dev.healthreminder.data.pojo.UiMetadataPojo
import com.dev.healthreminder.data.pojo.UserConfigurationPojo
import com.dev.healthreminder.data.pojo.UserConfigurePojo
import com.dev.healthreminder.domain.model.ConfigureItemModel
import com.dev.healthreminder.domain.model.IntervalModel
import com.dev.healthreminder.domain.model.ReminderModel
import com.dev.healthreminder.domain.model.TopPopupData

fun UserConfigurationPojo.toReminderModel() =
    ReminderModel(
        isActive = this.isActive,
        interval = this.interval,
    )

fun UserConfigurePojo.toConfigureItemModel(): ConfigureItemModel =
    ConfigureItemModel(
        reminderId = this.reminderId,
        uiMetadata = this.uiMetadata.toTopPopupData(),
        defaultIntervalList = this.defaultIntervalList.map { it.toIntervalModel() },
    )

fun UiMetadataPojo.toTopPopupData() =
    TopPopupData(
        displayTitle = this.displayTitle,
        description = this.description,
        ctaText = this.ctaText,
    )

fun IntervalPojo.toIntervalModel() =
    IntervalModel(
        intervalId = this.intervalId,
        intervalPlaceholder = this.intervalPlaceholder,
        intervalValueMillis = this.intervalValueMillis,
    )
