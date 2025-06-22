package com.waddleup.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.waddleup.app.theme.R

/**
 * Created on 5/12/2025
 * @author Kanan Bashir
 */

val plusJakartaSansFontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.plus_jakarta_sans_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic)
)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.poppins_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.poppins_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.poppins_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.poppins_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.poppins_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.poppins_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.poppins_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.poppins_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic)
)

/**
 * headline1 - 48sp
 * headline2 - 24sp
 * subtitle1 - 18sp
 * body1 - 16sp
 * body2 - 14sp
 * caption - 12sp
 * overline - 11sp
 * overline2 - 10sp
 * */
@Immutable
data class WaddleTypography(
    /**48 / 700*/ val headline1Bold: TextStyleParams = TextStyleParams(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    /**24 / 500*/ val headline2Medium: TextStyleParams = TextStyleParams(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    ),
    /**24 / 700*/ val headline2Bold: TextStyleParams = TextStyleParams(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    /**18 / 500*/ val subtitle1Medium: TextStyleParams = TextStyleParams(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    ),
    /**16 / 400*/ val body1Regular: TextStyleParams = TextStyleParams(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    /**16 / 500*/ val body1Medium: TextStyleParams = TextStyleParams(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    ),
    /**16 / 700*/ val body1Bold: TextStyleParams = TextStyleParams(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    /**14 / 600*/ val body2SemiBold: TextStyleParams = TextStyleParams(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal
    ),
    /**14 / 400*/ val body2Regular: TextStyleParams = TextStyleParams(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    /**14 / 500*/ val body2Medium: TextStyleParams = TextStyleParams(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    ),
    /**14 / 700*/ val body2Bold: TextStyleParams = TextStyleParams(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    /**12 / 300*/ val captionLight: TextStyleParams = TextStyleParams(
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal
    ),
    /**12 / 400*/ val captionRegular: TextStyleParams = TextStyleParams(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    /**12 / 500*/ val captionMedium: TextStyleParams = TextStyleParams(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    ),
    /**12 / 600*/ val captionSemiBold: TextStyleParams = TextStyleParams(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal
    ),
    /**11 / 500*/ val overlineMedium: TextStyleParams = TextStyleParams(
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    ),
    /**10 / 400*/ val overline2Regular: TextStyleParams = TextStyleParams(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    )
) {
    @Immutable
    @Suppress("PropertyName")
    data class TextStyleParams(
        internal val color: Color = Color.Unspecified,
        internal val fontSize: TextUnit = TextUnit.Unspecified,
        internal val fontWeight: FontWeight? = null,
        internal val fontStyle: FontStyle? = null,
        internal val fontSynthesis: FontSynthesis? = null,
        internal val fontFeatureSettings: String? = null,
        internal val letterSpacing: TextUnit = TextUnit.Unspecified,
        internal val baselineShift: BaselineShift? = null,
        internal val textGeometricTransform: TextGeometricTransform? = null,
        internal val localeList: LocaleList? = null,
        internal val background: Color = Color.Unspecified,
        internal val textDecoration: TextDecoration? = null,
        internal val shadow: Shadow? = null,
        internal val textAlign: TextAlign? = null,
        internal val textDirection: TextDirection? = null,
        internal val lineHeight: TextUnit = TextUnit.Unspecified,
        internal val textIndent: TextIndent? = null
    ) {
        val PlusJakarta: TextStyle = setTextStyleWith(plusJakartaSansFontFamily)
        val Poppins: TextStyle = setTextStyleWith(poppinsFontFamily)

        private fun TextStyleParams.setTextStyleWith(fontFamily: FontFamily) = TextStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFamily = fontFamily,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            textAlign = textAlign ?: TextAlign.Unspecified,
            textDirection = textDirection ?: TextDirection.Unspecified,
            lineHeight = lineHeight,
            textIndent = textIndent
        )
    }
}

val LocalAppTypography = staticCompositionLocalOf { WaddleTypography() }