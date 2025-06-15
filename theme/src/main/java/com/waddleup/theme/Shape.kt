package com.waddleup.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * Created on 5/12/2025
 * @author Kanan Bashir
 */

object CornerRadiusTokens {
    val extraSmall = 12.dp
    val small = 16.dp
    val medium = 32.dp
    val large = 48.dp
}

val LocalAppShapes = staticCompositionLocalOf {
    Shapes(
        extraSmall = RoundedCornerShape(CornerRadiusTokens.extraSmall),
        small = RoundedCornerShape(CornerRadiusTokens.small),
        medium = RoundedCornerShape(CornerRadiusTokens.medium),
        large = RoundedCornerShape(CornerRadiusTokens.large)
    )
}