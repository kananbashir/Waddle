package com.waddleup.core.presentation.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
internal fun ButtonText(
    text: String,
    color: Color
) {
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = text,
        color = color,
        style = WaddleTheme.typography.body2Medium.PlusJakarta,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}