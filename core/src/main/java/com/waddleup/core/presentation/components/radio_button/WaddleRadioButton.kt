package com.waddleup.core.presentation.components.radio_button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    radioButtonSize: Dp = 24.dp,
    unselectedBackgroundColor: Color = WaddleTheme.colors.background.primary,
    unselectedBorderColor: Color = WaddleTheme.colors.checkBoxes.secondaryBorder,
    selectedBackgroundColor: Color = WaddleTheme.colors.checkBoxes.primaryFilled,
    selectedBorderColor: Color = selectedBackgroundColor
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) selectedBackgroundColor else unselectedBackgroundColor,
        label = ""
    )

    val borderColor by animateColorAsState(
        targetValue = if (selected) selectedBorderColor else unselectedBorderColor,
        label = ""
    )

    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(radioButtonSize)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = CircleShape
                )
        ) {
            drawCircle(
                color = borderColor,
                style = Stroke(
                    width = 1f
                )
            )

            drawCircle(
                color = backgroundColor,
                radius = (size.minDimension / 2.0f) - 1f
            )
        }

        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                tint = unselectedBackgroundColor
            )
        }
    }
}

@Preview
@Composable
private fun WaddleRadioButtonPreview() {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(32.dp)
    ) {
        WaddleRadioButton(
            selected = isSelected,
            onClick = { isSelected = !isSelected }
        )
    }
}