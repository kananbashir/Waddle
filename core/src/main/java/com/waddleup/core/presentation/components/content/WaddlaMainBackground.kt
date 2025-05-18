package com.waddleup.core.presentation.components.content

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.indicator.loading_indicator.FullScreenLoadingIndicator
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WaddleMainContentWrapper(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
    backgroundColor: Color = WaddleTheme.colors.primaryBackground,
    isLoading: Boolean = false,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    onBack: (() -> Unit)? = null,
    blurRadius: Dp = 3.dp,
    content: @Composable () -> Unit
) {
    BackHandler(enabled = onBack != null) { onBack?.invoke() }

    Box(modifier = modifier.fillMaxSize().background(backgroundColor)) {
        Scaffold(
            modifier = Modifier
                .let { if (isLoading) it.blur(blurRadius) else it }
                .imePadding()
                .padding(paddingValues),
            containerColor = backgroundColor,
            topBar    = { topBar?.invoke() },
            bottomBar = { bottomBar?.invoke() },
            content   = { paddings ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddings.calculateTopPadding(),
                            bottom = paddings.calculateBottomPadding()
                        )
                ) {
                    content()
                }
            }
        )

        if (isLoading) {
            FullScreenLoadingIndicator(visible = true)
        }
    }
}