package com.waddleup.waddle.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.waddleup.core.base.viewmodel.state.UiEvent

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Any,
    onOnboardingCompleted: () -> Unit,
    onUiEvent: (UiEvent) -> Unit
) {
    val activity = LocalActivity.current as ComponentActivity

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        onboardingNavGraph(onOnboardingCompleted)
        authNavGraph(activity, onUiEvent)
        homeNavGraph(onUiEvent)
        statisticsNavGraph(onUiEvent)
        aiSurpriseNavGraph(onUiEvent)
        settingsNavGraph(onUiEvent)
        notificationNavGraph(onUiEvent)
        addIncomeSourceNavGraph(onUiEvent)
    }
}
