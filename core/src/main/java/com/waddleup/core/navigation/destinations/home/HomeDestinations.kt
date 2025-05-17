package com.waddleup.core.navigation.destinations.home

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Immutable
@Suppress("unused")
@Serializable
sealed class HomeDestinations {
    @Serializable
    data class HomeRoot(
        val someData: String
    ): HomeDestinations()
    @Serializable data object Home: HomeDestinations()
}