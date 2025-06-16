package com.waddleup.settings.viewmodel

import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.settings.viewmodel.state.SettingsIntent
import com.waddleup.settings.viewmodel.state.SettingsState

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

class SettingsViewModel(
    dispatchersProvider: DispatchersProvider
): BaseViewModel<SettingsState, SettingsIntent>(dispatchersProvider) {
    override val initialState: SettingsState
        get() = SettingsState()
}