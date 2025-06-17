package com.waddleup.core.presentation.components.switcher

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/16/2025
 * @author Kanan Bashir
 */

@Composable
fun MainSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    thumbGap: Dp = 2.dp,
    trackSize: DpSize = DpSize(51.dp, 31.dp),
    onCheckedChange: (Boolean) -> Unit
) {
    val colors = WaddleTheme.colors
    val density = LocalDensity.current

    val startPx by remember(trackSize, thumbGap, density) {
        mutableFloatStateOf(
            with(density) {
                (trackSize.height - (thumbGap * 2)).toPx() / 2
            }
        )
    }
    val endPx by remember(trackSize, thumbGap, density) {
        mutableFloatStateOf(
            with(density) {
                trackSize.width.toPx() -
                        (trackSize.height + (thumbGap * 2)).toPx() / 2
            }
        )
    }

    val thumbPosition by animateFloatAsState(
        targetValue = if (isChecked) endPx else startPx,
        animationSpec = tween(150, easing = FastOutSlowInEasing)
    )

    val trackColor by animateColorAsState(
        targetValue = if (isChecked) colors.buttons.primary else colors.indicators.tertiary,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = modifier
            .size(trackSize)
            .drawBehind {
                drawRoundRect(
                    color = trackColor,
                    size = trackSize.toSize(),
                    cornerRadius = CornerRadius(50f, 50f)
                )
            }
            .padding(thumbGap)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onCheckedChange(!isChecked) }
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(
                color = colors.background.primary,
                radius = (size.height) / 2,
                center = Offset(thumbPosition, size.height / 2)
            )
        }
    }
}


@Preview
@Composable
private fun MainSwitchPreview() {
    var isChecked by remember { mutableStateOf(true) }

    MainSwitch(
        isChecked = isChecked,
        onCheckedChange = { isChecked = !isChecked }
    )
}