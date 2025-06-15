package com.waddleup.settings.presentation.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.settings.presentation.component.SettingsInfoBox
import com.waddleup.settings.presentation.component.SettingsTopBar
import com.waddleup.settings.viewmodel.state.SettingsIntent
import com.waddleup.settings.viewmodel.state.SettingsState
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@Composable
fun SettingsContent(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    val colors = WaddleTheme.colors
    val shapes = WaddleTheme.shapes
    val types = WaddleTheme.typography

    WaddleMainContentWrapper(
        paddingValues = PaddingValues(),
        includeBottomNavPadding = true,

        topBar = {
            SettingsTopBar(
                onBackClicked = { onEvent(UiEvent.NavigateBack) }
            )
        },

        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                item {
                    VerticalSpacer(16.dp)

                    SettingsInfoBox(
                        modifier = Modifier
                            .padding(horizontal = (16.5).dp),
                        onCloseClicked = {}
                    )
                }
            }
        }
    )
}