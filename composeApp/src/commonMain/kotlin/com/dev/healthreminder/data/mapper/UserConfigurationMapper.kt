package com.dev.healthreminder.data.mapper

import com.dev.healthreminder.data.pojo.UserConfigurationPojo
import com.dev.healthreminder.domain.model.ReminderModel

fun UserConfigurationPojo.toModel() =
    ReminderModel(
        isActive = isActive,
        interval = interval,
    )
