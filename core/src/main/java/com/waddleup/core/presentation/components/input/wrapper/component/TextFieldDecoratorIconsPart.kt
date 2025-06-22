package com.waddleup.core.presentation.components.input.wrapper.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Composable
internal fun TextFieldLeadingIconSection(
    icon: (@Composable () -> Unit)?,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    TextFieldIconSection(
        icon,
        iconTint,
        modifier
            .padding(end = 12.dp)
    )
}

@Composable
internal fun TextFieldTrailingIconSection(
    icon: (@Composable () -> Unit)?,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    TextFieldIconSection(
        icon,
        iconTint,
        modifier
            .padding(start = 12.dp)
    )
}

@Composable
internal fun TextFieldIconSection(
    updatedIcon: (@Composable () -> Unit)?,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    updatedIcon?.let {
        Box(
            modifier = modifier.padding(vertical = 9.dp),
            content = {
                CompositionLocalProvider(
                    LocalContentColor provides iconTint,
                    content = it
                )
            }
        )
    }
}