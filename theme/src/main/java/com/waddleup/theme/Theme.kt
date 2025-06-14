package com.waddleup.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created on 5/12/2025
 * @author Kanan Bashir
 */

object WaddleTheme {
    val colors: WaddleAppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val typography: WaddleTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}

@Composable
fun WaddleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val typography = WaddleTheme.typography
    val shapes = WaddleTheme.shapes
    val color = if (darkTheme) DarkAppColors else LightAppColors
    val systemUiController = rememberSystemUiController()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = !darkTheme
            )

            systemUiController.setNavigationBarColor(
                color = color.background.primary,
                darkIcons = !darkTheme
            )
        }
    }

    CompositionLocalProvider(
        LocalAppColors provides color,
        LocalAppTypography provides typography,
        LocalAppShapes provides shapes
    ) {
        ProvideTextStyle(typography.body1Regular.PlusJakarta) {
            content()
        }
    }
}