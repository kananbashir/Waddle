package com.waddleup.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@Immutable
data class BackgroundColors(
    val primary: Color,
    val secondary: Color
)

@Immutable
data class TextColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val quaternary: Color,
    val quinary: Color,
    val infoHeader: Color
)

@Immutable
data class ButtonColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color
)

@Immutable
data class InputFieldColors(
    val primaryBackground: Color,
    val primaryText: Color,
    val primaryHint: Color,
    val primaryError: Color
)

@Immutable
data class ContainerColors(
    val primaryBackground: Color,
    val primaryOptionText: Color,
    val primaryText: Color,
    val primaryActionText: Color,
    val secondaryBackground: Color,
    val secondaryText: Color,
    val tertiaryBackground: Color
)

@Immutable
data class IndicatorColors(
    val primary: Color,
    val secondary: Color
)

@Immutable
data class DividerColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color
)

@Immutable
data class CheckBoxColors(
    val primaryFilled: Color,
    val primaryBorder: Color,
    val primaryMark: Color,
    val primaryError: Color
)

@Immutable
data class IconColors(
    val primaryTint: Color,
    val secondaryTint: Color,
    val tertiaryTint: Color,
    val quaternaryTint: Color,
    val quaternaryBackground: Color
)

@Immutable
data class BottomNavColors(
    val activeIcon: Color,
    val inactiveIcon: Color
)

@Immutable
data class InfoBoxColors(
    val primaryBackground: Color,
    val primaryForeground: Color,
    val primaryForeground2: Color
)



