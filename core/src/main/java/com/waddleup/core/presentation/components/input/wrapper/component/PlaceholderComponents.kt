package com.waddleup.core.presentation.components.input.wrapper.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Composable
internal fun PlaceholderTextSection(
    placeHolderText: String,
    text: String,
    isTyping: Boolean,
    isFocused: Boolean,
    colors: TextFieldColors,
    animateTextSize: Boolean,
    modifier: Modifier = Modifier,
) {
    if (animateTextSize) {
        PlaceholderText(placeHolderText, isTyping, isFocused, colors, true, modifier)
    } else {
        AnimatedVisibility(
            visible = text.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PlaceholderText(placeHolderText, isTyping, isFocused, colors, false, modifier)
        }
    }
}

@Composable
internal fun PlaceholderText(
    text: String,
    isTyping: Boolean,
    isFocused: Boolean,
    colors: TextFieldColors,
    animateTextSize: Boolean,
    modifier: Modifier = Modifier
) {
    val types = WaddleTheme.typography
    val mainTextStyleParam = types.body1Medium.PlusJakarta

    val animatedPlaceholderTextSize = types.run {
        animateFloatAsState(
            targetValue = when {
                isTyping -> overlineMedium.PlusJakarta.fontSize.value
                else -> mainTextStyleParam.fontSize.value
            },
            label = ""
        )
    }

    val textColor = if (isFocused) colors.focusedPlaceholderColor
    else colors.unfocusedPlaceholderColor

    val fontSize = if (animateTextSize) animatedPlaceholderTextSize.value.sp
    else mainTextStyleParam.fontSize

    Text(
        modifier = modifier
            .padding(
                top = if (isTyping && animateTextSize) 4.dp else 9.dp,
                bottom = if (isTyping && animateTextSize) 0.dp else 9.dp
            ),
        text = text,
        style = mainTextStyleParam.copy(fontSize = fontSize),
        color = textColor,
        overflow = TextOverflow.Ellipsis,
    )
}