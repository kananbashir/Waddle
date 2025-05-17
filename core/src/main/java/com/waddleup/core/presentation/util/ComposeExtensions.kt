package com.waddleup.core.presentation.util

import androidx.compose.ui.Modifier

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier = if (condition) {
    this.then(modifier(Modifier))
} else {
    this
}