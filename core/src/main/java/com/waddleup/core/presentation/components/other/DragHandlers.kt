package com.waddleup.core.presentation.components.other

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/28/2025
 * @author Kanan Bashir
 */

@Composable
fun DragHandler(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    width: Dp = 33.dp,
    color: Color = WaddleTheme.colors.secondaryIndicator
) {
    HorizontalDivider(
        modifier = modifier
            .width(width)
            .clip(CircleShape),
        thickness = thickness,
        color = color
    )
}