package com.waddleup.core.presentation.components.input

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
internal fun AnimatedErrorMessage(
    errorState: Boolean,
    errorText: String,
    modifier: Modifier = Modifier,
    animationDuration: Int = 300
) {
    val textMeasurer = rememberTextMeasurer()
    val style = WaddleTheme.typography.captionMedium.Poppins
    val textLayoutResult = textMeasurer.measure(
        errorText,
        style
    )
    val heightInPx = remember { textLayoutResult.size.height }

    val transition = updateTransition(targetState = errorState, label = "error_message_transition")

    val offsetY by transition.animateDp(
        transitionSpec = { tween(durationMillis = animationDuration) },
        label = "offset"
    ) { state -> if (state) 0.dp else (-heightInPx).dp }

    val delay = 100

    Box(
        modifier = modifier
            .zIndex(-1f)
            .offset(y = offsetY)
    ) {
        AnimatedVisibility(
            visible = errorState,
            enter = fadeIn(tween(animationDuration, delayMillis = delay)) + slideInVertically {
                it
            }
        ) {
            AnimatedContent(
                targetState = errorText,
                transitionSpec = {
                    fadeIn(tween(animationDuration, delayMillis = delay)) + slideInVertically {
                        -it
                    } togetherWith fadeOut(tween(animationDuration, delayMillis = delay)) + slideOutVertically {
                        -it
                    }
                },
                label = ""
            ) { text ->
                Text(
                    text = text,
                    style = style,
                    overflow = TextOverflow.Ellipsis,
                    color = WaddleTheme.colors.inputFieldError
                )
            }
        }
    }
}

@Composable
internal fun WaddleTextFieldText(
    text: String?,
    style: TextStyle = WaddleTheme.typography.body1Medium.Poppins,
) {
    Text(
        text = text ?: "-",
        style = style,
        overflow = TextOverflow.Ellipsis
    )
}