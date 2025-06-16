package com.waddleup.core.presentation.components.checkbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    size: Float = 20f,
    checkedColor: Color = WaddleTheme.colors.checkBoxes.primaryFilled,
    uncheckedColor: Color = WaddleTheme.colors.checkBoxes.primaryBorder,
    disabledCheckedColor: Color = WaddleTheme.colors.checkBoxes.primaryFilled.copy(0.2f),
    disabledUncheckedColor: Color = WaddleTheme.colors.checkBoxes.primaryBorder.copy(0.2f),
    errorColor: Color = WaddleTheme.colors.checkBoxes.primaryError,
    disabledErrorColor: Color = WaddleTheme.colors.checkBoxes.primaryError.copy(0.2f),
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(
        when {
            isError -> if (isEnabled) errorColor else disabledErrorColor
            isChecked -> if (isEnabled) checkedColor else disabledCheckedColor
            else -> if (isEnabled) uncheckedColor else disabledUncheckedColor
        },
        label = ""
    )
    val duration = 400

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .toggleable(
                indication = null,
                interactionSource = null,
                enabled = isEnabled,
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(
                    color = if (isChecked) checkboxColor else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = if (isError || isChecked) checkboxColor else uncheckedColor,
                    shape = RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = fadeIn(tween(duration)),
                exit = fadeOut(tween(duration))
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Check mark",
                    tint = WaddleTheme.colors.checkBoxes.primaryMark
                )
            }
        }
    }
}