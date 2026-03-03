package com.dev.healthreminder.domain.model

data class ConfigureItemModel(
    val reminderId: Long,
    val uiMetadata: TopPopupData,
    val defaultIntervalList: List<IntervalModel>,
)

data class IntervalModel(
    val intervalId: Long,
    val intervalPlaceholder: String,
    val intervalValueMillis: Long,
)

data class TopPopupData(
    val displayTitle: String,
    val description: String,
    val ctaText: String,
)
