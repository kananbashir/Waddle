package com.waddleup.core.presentation.components.content

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
    backgroundColor: Color = WaddleTheme.colors.background.primary,
    isLoading: Boolean = false,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    onBack: (() -> Unit)? = null,
    blurRadius: Dp = 3.dp,
    includeBottomNavPadding: Boolean = false,
    content: @Composable (paddings: PaddingValues) -> Unit
) {
    val density = LocalDensity.current

    val imeBottomDp = with(density) {
        WindowInsets
            .ime
            .only(WindowInsetsSides.Bottom)
            .getBottom(this)
            .toDp()
    }

    val navBarBottomDp = with(density) {
        WindowInsets
            .navigationBars
            .only(WindowInsetsSides.Bottom)
            .getBottom(this)
            .toDp()
    }

    val keyboardOnlyInset = (imeBottomDp - navBarBottomDp).coerceAtLeast(0.dp)

    BackHandler(enabled = onBack != null) { onBack?.invoke() }

    Box(
        modifier = modifier
            .padding(bottom = if (includeBottomNavPadding) 55.dp else 0.dp)
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Scaffold(
            modifier = Modifier
                .let { if (isLoading) it.blur(blurRadius) else it }
                .padding(paddingValues)
                .padding(bottom = keyboardOnlyInset),
            contentWindowInsets = WindowInsets(0),
            containerColor = backgroundColor,
            topBar    = {
                Box(
                    modifier = Modifier.statusBarsPadding()
                ) {
                    topBar?.invoke()
                }
            },
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
                    content(paddings)
                }
            }
        )

        if (isLoading) {
            FullScreenLoadingIndicator(visible = true)
        }
    }
}