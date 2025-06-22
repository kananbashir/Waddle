package com.waddleup.core.presentation.components.input.wrapper.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Composable
internal fun TextFieldTextSection(
    placeholderText: String?,
    text: String,
    isTyping: Boolean,
    isFocused: Boolean,
    colors: TextFieldColors,
    animateTextSize: Boolean,
    innerTextField: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        placeholderText?.let {
            PlaceholderTextSection(
                placeholderText,
                text,
                isTyping,
                isFocused,
                colors,
                animateTextSize
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = if (isTyping && animateTextSize) 23.dp else 9.dp,
                    bottom = if (isTyping && animateTextSize) 0.dp else 9.dp
                )
        ) {
            innerTextField()
        }
    }
}