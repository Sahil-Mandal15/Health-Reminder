package com.dev.healthreminder.domain.model

import healthreminder.composeapp.generated.resources.Res
import healthreminder.composeapp.generated.resources.ic_blink
import healthreminder.composeapp.generated.resources.ic_placeholder
import healthreminder.composeapp.generated.resources.ic_posture
import healthreminder.composeapp.generated.resources.ic_water
import org.jetbrains.compose.resources.DrawableResource

enum class ReminderFeature(
    val id: Long,
    val icon: DrawableResource,
) {
    WATER(1001, Res.drawable.ic_water),
    BLINK(1002, Res.drawable.ic_blink),
    POSTURE(1003, Res.drawable.ic_posture),
    ;

    companion object {
        fun getIconById(reminderId: Long): DrawableResource = entries.find { it.id == reminderId }?.icon ?: Res.drawable.ic_placeholder
    }
}
