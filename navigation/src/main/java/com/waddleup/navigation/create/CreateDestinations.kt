package com.waddleup.navigation.create

import kotlinx.serialization.Serializable

/**
 * Created on 4/14/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class CreateDestinations {
    @Serializable data object CreateRoot: CreateDestinations()
    @Serializable data object Create: CreateDestinations()
}