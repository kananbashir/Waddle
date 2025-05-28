package com.waddleup.core.presentation.components.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddlePrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    buttonText: String? = null,
    shape: Shape = WaddleTheme.shapes.small,
    colors: ButtonColors = ButtonDefaults.primaryWaddleColors(),
    hideKeyboardOnClick: Boolean = true,
) {
    WaddleButtonWrapper(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = shape,
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(),
        border = null,
        interactionSource = null,
        hideKeyboardOnClick = hideKeyboardOnClick,
        buttonText = buttonText,
    )
}

@Composable
fun WaddlePrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = WaddleTheme.shapes.small,
    colors: ButtonColors = ButtonDefaults.primaryWaddleColors(),
    hideKeyboardOnClick: Boolean = true,
    content: @Composable (RowScope.() -> Unit)? = null
) {
    WaddleButtonWrapper(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = shape,
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(),
        border = null,
        interactionSource = null,
        hideKeyboardOnClick = hideKeyboardOnClick,
        content = content,
    )
}


@Composable
fun WaddleSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    buttonText: String? = null,
    shape: Shape = WaddleTheme.shapes.small,
    hideKeyboardOnClick: Boolean = true,
) {
    WaddlePrimaryButton(
        onClick = onClick,
        modifier = modifier,
        isEnabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.secondaryWaddleColors(),
        hideKeyboardOnClick = hideKeyboardOnClick,
        buttonText = buttonText,
    )
}

@Composable
fun WaddleSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = WaddleTheme.shapes.small,
    hideKeyboardOnClick: Boolean = true,
    content: @Composable (RowScope.() -> Unit)? = null
) {
    WaddlePrimaryButton(
        onClick = onClick,
        modifier = modifier,
        isEnabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.secondaryWaddleColors(),
        hideKeyboardOnClick = hideKeyboardOnClick,
        content = content,
    )
}