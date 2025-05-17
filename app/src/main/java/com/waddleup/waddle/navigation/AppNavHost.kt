package com.waddleup.waddle.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.waddleup.waddle.navigation.ai_surprise.aiSurpriseNavGraph
import com.waddleup.app.navigation.auth.authNavGraph
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.waddle.navigation.home.homeNavGraph
import com.waddleup.waddle.navigation.onboarding.onboardingNavGraph
import com.waddleup.waddle.navigation.settings.settingsNavGraph
import com.waddleup.waddle.navigation.statictics.statisticsNavGraph

/**
 * Created on 4/10/2025
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
        homeNavGraph(onUiEvent, navController)
        statisticsNavGraph(onUiEvent)
        aiSurpriseNavGraph(onUiEvent)
        settingsNavGraph(onUiEvent)
    }
}
