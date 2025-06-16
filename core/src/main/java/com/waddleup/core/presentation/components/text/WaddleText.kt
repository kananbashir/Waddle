package com.waddleup.core.presentation.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.waddleup.core.presentation.components.text.util.ClickableTextParams
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleAutoResizedText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    style: TextStyle,
    color: Color = WaddleTheme.colors.text.primary,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
) {
    var resizedTextStyle by remember { mutableStateOf(style) }
    var shouldDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines,
        softWrap = false,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.8
                )
            } else {
                shouldDraw = true
            }
        }
    )
}

@Composable
fun WaddleAutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    color: Color = WaddleTheme.colors.text.primary,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
) {
    var resizedTextStyle by remember { mutableStateOf(style) }
    var shouldDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines,
        softWrap = false,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.8
                )
            } else {
                shouldDraw = true
            }
        }
    )
}

@Composable
fun WaddleClickableText(
    fullText: String,
    links: List<ClickableTextParams>,
    textContent: @Composable ((fullText: AnnotatedString) -> Unit)? = null
) {
    val annotatedLinkStr: AnnotatedString = buildAnnotatedString {
        append(fullText)

        links.forEach { params ->
            val startIndex = fullText.indexOf(params.text).run {
                if (this != -1) this else -1
            }
            val endIndex = (startIndex + params.text.length).coerceAtMost(fullText.length)

            addLink(
                LinkAnnotation.Clickable(
                    tag = params.tag,
                    styles = TextLinkStyles(
                        style = params.style.run {
                            SpanStyle(
                                color = params.color,
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
                            )
                        },
                    ),
                    linkInteractionListener = params.linkInteractionListener,
                ),
                startIndex,
                endIndex
            )
        }
    }

    textContent?.invoke(annotatedLinkStr)
}

@Composable
fun WaddleClickableText(
    modifier: Modifier = Modifier,
    fullText: String,
    textColor: Color,
    textStyle: TextStyle,
    links: List<ClickableTextParams>
) {
    val annotatedLinkStr: AnnotatedString = buildAnnotatedString {
        append(fullText)

        links.forEach { params ->
            val startIndex = fullText.indexOf(params.text).run {
                if (this != -1) this else -1
            }
            val endIndex = (startIndex + params.text.length).coerceAtMost(fullText.length)

            addLink(
                LinkAnnotation.Clickable(
                    tag = params.tag,
                    styles = TextLinkStyles(
                        style = params.style.run {
                            SpanStyle(
                                color = params.color,
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
                            )
                        },
                    ),
                    linkInteractionListener = params.linkInteractionListener,
                ),
                startIndex,
                endIndex
            )
        }
    }

    Text(
        modifier = modifier,
        text = annotatedLinkStr,
        style = textStyle,
        color = textColor
    )
}