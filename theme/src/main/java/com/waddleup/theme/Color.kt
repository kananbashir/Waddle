package com.waddleup.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Created on 5/12/2025
 * @author Kanan Bashir
 */

val Black = Color(0xFF010203)
val NightBlack = Color(0xFF110F0F)
val EerieBlack = Color(0xFF121212)
val JetGray = Color(0xFF333333)
val DavyGray = Color(0xFF5C5C5C)
val OldSilver = Color(0xFF858585)
val BattleshipGrey = Color(0xFF868686)
val CadetGray = Color(0xFFB6B8BE)
val FrenchGray = Color(0xFFB5B2C1)
val WhiteSmoke = Color(0xFFF4F4F4)
val AntiFlashWhite = Color(0xFFF3F6FA)
val PlatinumWhite = Color(0xFFE3E3E6)
val PalePlatinumWhite = Color(0xFFE3E3E6)
val CarrotOrange = Color(0xFFF19A37)
val CrayolaRed = Color(0xFFEB445A)
val GlaucousBlue = Color(0xFF507DBC)
val KellyGreen = Color(0xFF28B525)

@Immutable
data class WaddleAppColors (
    val primaryBackground: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val primaryButton: Color,
    val onPrimaryButton: Color,
    val secondaryButton: Color,
    val onSecondaryButton: Color,
    val inputFieldBackground: Color,
    val inputFieldHint: Color,
    val inputFieldText: Color,
    val inputFieldError: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val primaryContainerBackground: Color,
    val primaryContainerOptionText: Color,
    val primaryContainerText: Color,
    val primaryContainerActionText: Color,
    val primaryIndicator: Color,
    val secondaryIndicator: Color,
    val primaryDivider: Color,
    val success: Color,
    val primaryCheckBoxFilled: Color,
    val primaryCheckBoxBorder: Color,
    val primaryCheckMark: Color,
    val primaryCheckBoxError: Color,
    val primaryButtonTint: Color,
    val activeBottomNavItem: Color,
    val nonActiveBottomNavItem: Color
)

val LightAppColors = WaddleAppColors(
    primaryBackground = Color.White,
    primaryText = Black,
    secondaryText = DavyGray,
    primaryButton = GlaucousBlue,
    onPrimaryButton = Color.White,
    secondaryButton = PlatinumWhite,
    onSecondaryButton = Black,
    inputFieldBackground = WhiteSmoke,
    inputFieldHint = CadetGray,
    inputFieldText = Black,
    inputFieldError = CrayolaRed,
    tertiary = AntiFlashWhite,
    onTertiary = NightBlack,
    primaryContainerBackground = WhiteSmoke,
    primaryContainerOptionText = FrenchGray,
    primaryContainerText = Black,
    primaryContainerActionText = CarrotOrange,
    primaryIndicator = Black,
    secondaryIndicator = EerieBlack.copy(alpha = 0.1f),
    primaryDivider = PalePlatinumWhite,
    success = KellyGreen,
    primaryCheckBoxFilled = GlaucousBlue,
    primaryCheckBoxBorder = FrenchGray,
    primaryCheckMark = Color.White,
    primaryCheckBoxError = CrayolaRed,
    primaryButtonTint = JetGray,
    activeBottomNavItem = GlaucousBlue,
    nonActiveBottomNavItem = OldSilver
)

val DarkAppColors = WaddleAppColors(
    primaryBackground = Color.White,
    primaryText = Black,
    secondaryText = DavyGray,
    primaryButton = GlaucousBlue,
    onPrimaryButton = Color.White,
    secondaryButton = PlatinumWhite,
    onSecondaryButton = Black,
    inputFieldBackground = WhiteSmoke,
    inputFieldHint = CadetGray,
    inputFieldText = Black,
    inputFieldError = CrayolaRed,
    tertiary = AntiFlashWhite,
    onTertiary = NightBlack,
    primaryContainerBackground = WhiteSmoke,
    primaryContainerOptionText = FrenchGray,
    primaryContainerText = Black,
    primaryContainerActionText = CarrotOrange,
    primaryIndicator = Black,
    secondaryIndicator = EerieBlack.copy(alpha = 0.1f),
    primaryDivider = PalePlatinumWhite,
    success = KellyGreen,
    primaryCheckBoxFilled = GlaucousBlue,
    primaryCheckBoxBorder = FrenchGray,
    primaryCheckMark = Color.White,
    primaryCheckBoxError = CrayolaRed,
    primaryButtonTint = JetGray,
    activeBottomNavItem = GlaucousBlue,
    nonActiveBottomNavItem = OldSilver
)

val LocalAppColors = staticCompositionLocalOf { LightAppColors }
