package com.waddleup.waddle.viewmodel

import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.waddleup.app.domain.CheckOnboardingStatusUseCase
import com.waddleup.app.domain.CompleteOnboardingUseCase
import com.waddleup.auth.domain.usecase.CheckLoggedInStatusUseCase
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.navigation.auth.AuthRootDestination
import com.waddleup.navigation.home.HomeRootDestinations
import com.waddleup.navigation.onboarding.OnboardingRootDestination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class MainViewModel(
    private val checkLoggedInStatusUseCase: CheckLoggedInStatusUseCase,
    private val checkOnboardingStatusUseCase: CheckOnboardingStatusUseCase,
    private val completeOnboardingUseCase: CompleteOnboardingUseCase,
    dispatchersProvider: DispatchersProvider
): BaseViewModel<MainScreenState, MainScreenIntent>(dispatchersProvider) {

    override val initialState: MainScreenState
        get() = MainScreenState()

    private val _showDialog = Channel<UiEvent.ShowDialog?>()
    val showDialog = _showDialog.receiveAsFlow()

    private val _showError = Channel<UiEvent.ShowError?>()
    val showError = _showError.receiveAsFlow()

    private val isNavigating = AtomicBoolean(false)

    override fun onIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.CompleteOnboarding -> completeOnboarding()
            MainScreenIntent.TrySetStartDestination -> trySetStartDestination()
        }
    }

    fun dispatchEvent(event: UiEvent, navController: NavHostController) {
        when (event) {
            UiEvent.NavigateBack -> navController.navigateUp()
            is UiEvent.Navigate -> handleNavigationEvent(navController, event)
            is UiEvent.ShowDialog -> _showDialog.trySend(event)
            is UiEvent.ShowSnackbar -> {}
            is UiEvent.ShowToast -> {}
            is UiEvent.ShowError -> _showError.trySend(event)
        }
    }

    fun dismissDialog() = _showDialog.trySend(null)
    fun dismissErrorDialog() = _showError.trySend(null)

    private fun trySetStartDestination() {
        safeLaunch {
            val destination: Any = when {
                !checkOnboardingStatus() -> OnboardingRootDestination
                !checkLoggedInStatus() -> AuthRootDestination
                else -> HomeRootDestinations
            }

            setState {
                it.copy(
                    isLoading = false,
                    startDestination = destination
                )
            }
        }
    }

    private suspend fun checkOnboardingStatus() = suspendCoroutine { continuation ->
        execute(
            useCase = checkOnboardingStatusUseCase,
            onSuccess = {
                continuation.resume(it)
            },
            onError = { msg, _ ->  }
        )
    }

    private suspend fun checkLoggedInStatus() = suspendCoroutine { continuation ->
        execute(
            useCase = checkLoggedInStatusUseCase,
            onSuccess = {
                continuation.resume(it)
            },
            onError = { msg, _ -> }
        )
    }

    private fun completeOnboarding() {
        execute(
            useCase = completeOnboardingUseCase,
            onSuccess = {
                setState { it.copy(startDestination = AuthRootDestination) }
            },
            onError = { msg, _ -> }
        )
    }

    private fun handleNavigationEvent(navController: NavHostController, event: UiEvent.Navigate) {
        safeCoroutineScope.launch {
            if (!isNavigating.compareAndSet(false, true)) return@launch

            val currentDestination = navController.currentDestination
            val currentRoute = event.route::class.qualifiedName
            val isTheSame = currentDestination
                ?.hierarchy?.any { it.route == currentRoute }

            if (isTheSame == true) {
                delay(300)
                isNavigating.set(false)
                return@launch
            }

            navController.navigate(event.route) {
                if (event.popUpToStartDestination) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = event.saveState
                        inclusive = event.inclusive
                    }
                } else if (event.popUpTo != null) {
                    popUpTo(event.popUpTo ?: return@navigate) {
                        saveState = event.saveState
                        inclusive = event.inclusive
                    }
                }
                launchSingleTop = true
                restoreState = true
            }

            delay(300)
            isNavigating.set(false)
        }
    }
}