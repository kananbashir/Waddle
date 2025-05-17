package com.waddleup.core.navigation.destinations.settings

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class SettingsDestinations {
    @Serializable data object SettingsRoot: SettingsDestinations()
    @Serializable data object Settings: SettingsDestinations()
}