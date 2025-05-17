package com.waddleup.waddle.navigation.ai_surprise

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class AiSurpriseDestinations {
    @Serializable data object AiSurpriseRoot: AiSurpriseDestinations()
    @Serializable data object AiSurprise: AiSurpriseDestinations()
}