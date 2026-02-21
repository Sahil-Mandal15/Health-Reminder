package com.dev.healthreminder.data.pojo

data class UserConfigurationPojo(
    val reminderId: Long,
    val isActive: Boolean,
    val interval: Long,
)
