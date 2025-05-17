package com.waddleup.core.navigation.destinations.statistics

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class StatisticsDestinations {
    @Serializable data object StatisticsRoot: StatisticsDestinations()
    @Serializable data object Statistics: StatisticsDestinations()
}