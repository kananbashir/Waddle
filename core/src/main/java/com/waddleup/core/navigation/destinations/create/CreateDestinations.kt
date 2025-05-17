package com.waddleup.core.navigation.destinations.create

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class CreateDestinations {
    @Serializable data object CreateRoot: CreateDestinations()
    @Serializable data object Create: CreateDestinations()
}