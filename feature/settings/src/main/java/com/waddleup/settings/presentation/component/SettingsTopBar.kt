package com.waddleup.settings.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.waddleup.core.presentation.components.content.WaddleMainTopBar
import com.waddleup.app.theme.R

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@Composable
fun SettingsTopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    WaddleMainTopBar(
        modifier = modifier,
        title = R.string.text_settings,
        onBackClicked = onBackClicked
    )
}