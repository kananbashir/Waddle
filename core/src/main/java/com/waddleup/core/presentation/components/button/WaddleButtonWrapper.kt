package com.waddleup.core.presentation.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.util.checkValues
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
internal fun WaddleButtonWrapper(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    colors: ButtonColors,
    enabled: Boolean,
    elevation: ButtonElevation?,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    hideKeyboardOnClick: Boolean = true,
    buttonText: String? = null,
    content: @Composable (RowScope.() -> Unit)? = null
) {
    rememberSaveable { checkValues(buttonText, content) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val disabledContainerColor by animateColorAsState(
        targetValue = when (enabled) {
            true -> colors.containerColor
            else -> colors.disabledContainerColor
        },
        animationSpec = tween(800),
        label = "disabled_container_color"
    )

    val displayText by rememberSaveable(buttonText) { mutableStateOf(buttonText) }

    Button(
        onClick = {
            if (hideKeyboardOnClick) keyboardController?.hide()
            onClick()
        },
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors.copy(
            disabledContainerColor = disabledContainerColor
        ),
        elevation = elevation,
        border = border,
        contentPadding = PaddingValues(0.dp),
        interactionSource = interactionSource,
        content = {
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Max),
                contentAlignment = Alignment.Center
            ) {
                when {
                    displayText != null -> ButtonText(
                        displayText!!,
                        if (enabled.not()) colors.disabledContentColor
                        else colors.contentColor
                    )
                    else -> content?.let { this@Button.it() }
                }
            }
        }
    )
}

@Composable
fun ButtonDefaults.primaryWaddleColors() = WaddleTheme.colors.buttons.run {
    buttonColors(
        containerColor = primary,
        contentColor = onPrimary,
        disabledContainerColor = primary.copy(alpha = 0.2f),
        disabledContentColor = onPrimary
    )
}

@Composable
fun ButtonDefaults.secondaryWaddleColors() = WaddleTheme.colors.buttons.run {
    buttonColors(
        containerColor = secondary,
        contentColor = onSecondary,
        disabledContainerColor = secondary.copy(alpha = 0.2f),
        disabledContentColor = onSecondary
    )
}

@Composable
fun ButtonDefaults.tertiaryWaddleColors() = WaddleTheme.colors.buttons.run {
    buttonColors(
        containerColor = tertiary,
        contentColor = onTertiary,
        disabledContainerColor = tertiary.copy(alpha = 0.2f),
        disabledContentColor = onTertiary
    )
}