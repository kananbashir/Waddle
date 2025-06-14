package com.waddleup.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import com.waddleup.theme.color.BackgroundColors
import com.waddleup.theme.color.BottomNavColors
import com.waddleup.theme.color.ButtonColors
import com.waddleup.theme.color.CheckBoxColors
import com.waddleup.theme.color.ContainerColors
import com.waddleup.theme.color.DividerColors
import com.waddleup.theme.color.IconColors
import com.waddleup.theme.color.IndicatorColors
import com.waddleup.theme.color.InputFieldColors
import com.waddleup.theme.color.Palette
import com.waddleup.theme.color.TextColors

/**
 * Created on 5/12/2025
 * @author Kanan Bashir
 */

@Immutable
data class WaddleAppColors (
    val background: BackgroundColors,
    val text: TextColors,
    val buttons: ButtonColors,
    val inputFields: InputFieldColors,
    val containers: ContainerColors,
    val indicators: IndicatorColors,
    val dividers: DividerColors,
    val checkBoxes: CheckBoxColors,
    val icons: IconColors,
    val bottomNav: BottomNavColors
)

val LightAppColors = WaddleAppColors(
    background = BackgroundColors(
        primary = Palette.White,
        secondary = Palette.GlaucousBlue
    ),

    text = TextColors(
        primary = Palette.Black,
        secondary = Palette.DavyGray,
        tertiary = Palette.White,
        quaternary = Palette.RhythmSilver,
        quinary = Palette.JetGray
    ),

    buttons = ButtonColors(
        primary = Palette.GlaucousBlue,
        onPrimary = Palette.White,
        secondary = Palette.PlatinumWhite,
        onSecondary = Palette.Black
    ),

    inputFields = InputFieldColors(
        primaryBackground = Palette.WhiteSmoke,
        primaryText = Palette.Black,
        primaryHint = Palette.CadetGray,
        primaryError = Palette.CrayolaRed
    ),

    containers = ContainerColors(
        primaryBackground = Palette.WhiteSmoke,
        primaryOptionText = Palette.FrenchGray,
        primaryText = Palette.Black,
        primaryActionText = Palette.CarrotOrange,
        secondaryBackground = Palette.Black,
        secondaryText = Palette.White,
        tertiaryBackground = Palette.BrightGray
    ),

    indicators = IndicatorColors(
        primary = Palette.Black,
        secondary = Palette.EerieBlack.copy(alpha = 0.1f)
    ),

    dividers = DividerColors(
        primary = Palette.PalePlatinumWhite,
        secondary = Palette.LotionWhite
    ),

    checkBoxes = CheckBoxColors(
        primaryFilled = Palette.GlaucousBlue,
        primaryBorder = Palette.FrenchGray,
        primaryMark = Palette.White,
        primaryError = Palette.CrayolaRed
    ),

    bottomNav = BottomNavColors(
        activeIcon = Palette.GlaucousBlue,
        inactiveIcon = Palette.OldSilver
    ),

    icons = IconColors(
        primaryTint = Palette.GlaucousBlue,
        secondaryTint = Palette.White,
        tertiaryTint = Palette.JetGray
    )
)

val DarkAppColors = LightAppColors

val LocalAppColors = staticCompositionLocalOf { LightAppColors }
