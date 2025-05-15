package com.waddleup.core.presentation.components.text.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.TextStyle

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

data class ClickableTextParams(
    val tag: String,
    val text: String,
    val color: Color,
    val style: TextStyle,
    var linkInteractionListener: LinkInteractionListener? = null
)