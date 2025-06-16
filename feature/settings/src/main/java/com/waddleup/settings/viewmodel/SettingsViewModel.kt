package com.waddleup.settings.viewmodel

import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.settings.viewmodel.state.SettingsIntent
import com.waddleup.settings.viewmodel.state.SettingsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

class SettingsViewModel(
    dispatchersProvider: DispatchersProvider
): BaseViewModel<SettingsState, SettingsIntent>(dispatchersProvider) {
    override val initialState: SettingsState
        get() = SettingsState()

    init {
        safeCoroutineScope.launch {
            delay(500)
            setState { it.copy(showInfoBox = true) }
        }
    }

    override fun onIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.InfoBoxCloseClicked -> setState { it.copy(showInfoBox = false) }
        }
    }
}