package com.waddleup.settings.viewmodel

import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.settings.presentation.model.SettingsAction
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
        setState { it.copy(isPushNotificationsToggled = false) }
        safeCoroutineScope.launch {
            delay(500)
            setState { it.copy(showInfoBox = true) }
        }
    }

    override fun onIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.InfoBoxCloseClicked -> setState { it.copy(showInfoBox = false) }
            is SettingsIntent.SettingsActionClicked -> handleSettingsAction(intent.settingsAction)
        }
    }

    private fun handleSettingsAction(settingsAction: SettingsAction) {
        when (settingsAction) {
            SettingsAction.EditProfile -> {}
            SettingsAction.ChangePassword -> {}
            SettingsAction.ChangePinCode -> {}
            SettingsAction.PushNotifications -> togglePushNotification()
            SettingsAction.ChangeAppLanguage -> {}
            SettingsAction.SubscriptionPlan -> {}
            SettingsAction.SetSpendingLimits -> {}
            SettingsAction.CreateFinancialGoals -> {}
            SettingsAction.BudgetReminders -> {}
            SettingsAction.HelpCenter -> {}
            SettingsAction.Faq -> {}
        }
    }

    private fun togglePushNotification() {
        setState {
            it.copy(
                isPushNotificationsToggled = uiState.value.isPushNotificationsToggled?.not()
            )
        }
    }
}