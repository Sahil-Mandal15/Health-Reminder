package com.dev.healthreminder.data.pojo

data class UserConfigurePojo(
    val reminderId: Long,
    val uiMetadata: UiMetadataPojo,
    val defaultIntervalList: List<Long>,
)
