package com.waddleup.navigation.ai_surprise

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
sealed class AiSurpriseDestinations {
    @Serializable data object AiSurpriseRoot: AiSurpriseDestinations()
    @Serializable data object AiSurprise: AiSurpriseDestinations()
}