package com.waddleup.core.presentation.components.input.wrapper.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleTextFieldDecorator(
    placeholderText: String?,
    text: String,
    isTyping: Boolean,
    isFocused: Boolean,
    animateTextSize: Boolean,
    innerTextField: @Composable () -> Unit,
    leadingIcon: (@Composable () -> Unit)?,
    trailingIcon: (@Composable () -> Unit)?,
    leadingIconTint: Color,
    trailingIconTint: Color,
    colors: TextFieldColors,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.focusedContainerColor)
            .padding(vertical = 7.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextFieldLeadingIconSection(leadingIcon, leadingIconTint)

        TextFieldTextSection(
            placeholderText,
            text,
            isTyping,
            isFocused,
            colors,
            animateTextSize,
            innerTextField,
            modifier = Modifier.weight(1f, fill = true)
        )

        TextFieldTrailingIconSection(trailingIcon, trailingIconTint)
    }
}