package com.waddleup.navigation.home

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class HomeDestinations {
    @Serializable
    data object  HomeRoot: HomeDestinations()
    @Serializable data object Home: HomeDestinations()
}