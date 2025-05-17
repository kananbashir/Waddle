package com.waddleup.core.navigation.destinations.ai_surprise

import kotlinx.serialization.Serializable

/**
 * Created on 4/14/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class AiSurpriseDestinations {
    @Serializable data object AiSurpriseRoot: AiSurpriseDestinations()
    @Serializable data object AiSurprise: AiSurpriseDestinations()
}