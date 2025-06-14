package com.waddleup.waddle.navigation

import androidx.activity.ComponentActivity
import androidx.navigation.NavGraphBuilder
import com.waddleup.auth.login.presentation.content.LoginContent
import com.waddleup.auth.password_recovery.content.PasswordRecoveryContent
import com.waddleup.auth.register.presentation.content.RegisterContent
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.auth.viewmodel.AuthViewModel
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.content.BaseScreen
import com.waddleup.navigation.auth.AuthRootDestination
import com.waddleup.navigation.auth.AuthLoginDestination
import com.waddleup.navigation.auth.PasswordRecoveryDestination
import com.waddleup.navigation.auth.RegisterDestination
import com.waddleup.waddle.navigation.util.waddleComposable
import com.waddleup.waddle.navigation.util.waddleNavigation

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.authNavGraph(
    activity: ComponentActivity,
    onUiEvent: (UiEvent) -> Unit
) {
    waddleNavigation<AuthRootDestination>(AuthLoginDestination) {
        waddleComposable<AuthLoginDestination> {
            BaseScreen<AuthState, AuthIntent, AuthViewModel>(
                onUiEvent = onUiEvent,
                viewModelStoreOwner = activity,
                content = { viewModel, uiState ->
                    LoginContent(
                        state = uiState.value.loginState,
                        onIntent = viewModel::onIntent,
                        onEvent = viewModel::sendEvent
                    )
                }
            )
        }

        waddleComposable<RegisterDestination> {
            BaseScreen<AuthState, AuthIntent, AuthViewModel>(
                onUiEvent = onUiEvent,
                viewModelStoreOwner = activity,
                content = { viewModel, uiState ->
                    RegisterContent(
                        state = uiState.value.registerState,
                        onIntent = viewModel::onIntent,
                        onEvent = viewModel::sendEvent
                    )
                }
            )
        }

        waddleComposable<PasswordRecoveryDestination> {
            BaseScreen<AuthState, AuthIntent, AuthViewModel>(
                onUiEvent = onUiEvent,
                viewModelStoreOwner = activity,
                content = { viewModel, uiState ->
                    PasswordRecoveryContent(
                        state = uiState.value.passwordRecoveryState,
                        onIntent = viewModel::onIntent,
                        onEvent = viewModel::sendEvent
                    )
                }
            )
        }
    }
}