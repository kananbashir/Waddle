package com.waddleup.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * Created on 5/12/2025
 * @author Kanan Bashir
 */

val LocalAppShapes = staticCompositionLocalOf {
    Shapes(
        extraSmall = RoundedCornerShape(size = 12.dp),
        small = RoundedCornerShape(size = 16.dp),
        medium = RoundedCornerShape(size = 32.dp),
        large = RoundedCornerShape(size = 48.dp)
    )
}