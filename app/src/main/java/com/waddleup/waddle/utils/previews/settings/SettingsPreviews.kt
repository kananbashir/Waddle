package com.waddleup.waddle.utils.previews.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.waddleup.settings.presentation.content.SettingsContent
import com.waddleup.settings.viewmodel.state.SettingsState

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@Preview
@Composable
private fun SettingsContentPreview() {
    SettingsContent(
        state = SettingsState(),
        onIntent = {},
        onEvent = {}
    )
}