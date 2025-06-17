package com.waddleup.settings.viewmodel.state

import com.waddleup.settings.presentation.model.SettingsAction

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

sealed class SettingsIntent {
    data object InfoBoxCloseClicked: SettingsIntent()
    data class SettingsActionClicked(val settingsAction: SettingsAction): SettingsIntent()
}