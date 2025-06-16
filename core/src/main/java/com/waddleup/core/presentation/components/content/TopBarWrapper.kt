package com.waddleup.core.presentation.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.other.HorizontalSpacer

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@Composable
fun TopBarWrapper(
    modifier: Modifier = Modifier,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    textContent: @Composable () -> Unit
) {
    var leadingContentWidth by rememberSaveable { mutableIntStateOf(-1) }
    var trailingContentWidth by rememberSaveable { mutableIntStateOf(-1) }
    val textHorizontalPadding by remember(leadingContentWidth, trailingContentWidth) {
        derivedStateOf {
            if (leadingContentWidth > trailingContentWidth) leadingContentWidth.dp
            else trailingContentWidth.dp
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .onSizeChanged {
                        if (leadingContentWidth == -1) leadingContentWidth = it.width
                    },
                content = { leadingContent?.invoke() }
            )

            HorizontalSpacer(16.dp)

            Box(
                modifier = Modifier
                    .onSizeChanged {
                        if (trailingContentWidth == -1) trailingContentWidth = it.width
                    },
                content = { trailingContent?.invoke() }
            )
        }

        Box(
            modifier = Modifier
                .padding(
                    horizontal = textHorizontalPadding.coerceAtLeast(0.dp)
                ),
            content = { textContent() }
        )
    }
}