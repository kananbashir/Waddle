package com.waddleup.home.domain

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Created on 5/28/2025
 * @author Kanan Bashir
 */

@Immutable
data class TransactionCategory(
    val color: Color,
    val categoryName: String
)
