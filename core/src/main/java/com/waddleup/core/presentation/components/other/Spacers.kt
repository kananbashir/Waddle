package com.waddleup.core.presentation.components.other

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Created on 5/28/2025
 * @author Kanan Bashir
 */

@Composable
fun VerticalSpacer(
    heightDp: Dp
) {
    Spacer(modifier = Modifier.height(heightDp))
}

@Composable
fun HorizontalSpacer(
    widthDp: Dp
) {
    Spacer(modifier = Modifier.width(widthDp))
}