package com.waddleup.waddle.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.content.BaseScreen
import com.waddleup.navigation.settings.SettingsDestination
import com.waddleup.navigation.settings.SettingsRootDestination
import com.waddleup.settings.presentation.content.SettingsContent
import com.waddleup.settings.viewmodel.SettingsViewModel
import com.waddleup.settings.viewmodel.state.SettingsIntent
import com.waddleup.settings.viewmodel.state.SettingsState
import com.waddleup.waddle.navigation.util.stayAnim
import com.waddleup.waddle.navigation.util.waddleComposable
import com.waddleup.waddle.navigation.util.waddleNavigation

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.settingsNavGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    waddleNavigation<SettingsRootDestination>(SettingsDestination) {
        waddleComposable<SettingsDestination>(
            enterTransition = { fadeIn() },
            exitTransition = { stayAnim },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            BaseScreen<SettingsState, SettingsIntent, SettingsViewModel>(
                onUiEvent = onUiEvent,
                content = { viewModel, state ->
                    SettingsContent(
                        state = state.value,
                        onIntent = viewModel::onIntent,
                        onEvent = viewModel::sendEvent
                    )
                }
            )
        }
    }
}