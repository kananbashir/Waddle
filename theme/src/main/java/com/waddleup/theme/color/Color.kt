package com.waddleup.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

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
    val bottomNav: BottomNavColors,
    val infoBoxes: InfoBoxColors
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
        quinary = Palette.JetGray,
        infoHeader = Palette.PhilippineSilver
    ),

    buttons = ButtonColors(
        primary = Palette.GlaucousBlue,
        onPrimary = Palette.White,
        secondary = Palette.PlatinumWhite,
        onSecondary = Palette.Black,
        tertiary = Palette.WhiteSmoke,
        onTertiary = Palette.Black
    ),

    inputFields = InputFieldColors(
        primaryBackground = Palette.WhiteSmoke,
        primaryText = Palette.Black,
        primaryHint = Palette.CadetGray,
        secondaryHint = Palette.FrenchGray,
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
        secondary = Palette.EerieBlack.copy(alpha = 0.1f),
        tertiary = Palette.BrightSilverGray
    ),

    dividers = DividerColors(
        primary = Palette.PalePlatinumWhite,
        secondary = Palette.LotionWhite,
        tertiary = Palette.BrightGray
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
        tertiaryTint = Palette.JetGray,
        quaternaryTint = Palette.Black,
        quaternaryBackground = Palette.GlaucousBlue.copy(alpha = 0.5f)
    ),
    
    infoBoxes = InfoBoxColors(
        primaryBackground = Palette.Black,
        primaryForeground = Palette.GlaucousBlue,
        primaryForeground2 = Palette.SpaceCadetBlue,
        secondaryBackground = Palette.AntiFlashWhite,
        onSecondaryBackground = Palette.NightBlack
    )
)

val DarkAppColors = LightAppColors

val LocalAppColors = staticCompositionLocalOf { LightAppColors }
