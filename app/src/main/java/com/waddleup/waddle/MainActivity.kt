package com.waddleup.waddle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.waddleup.waddle.navigation.AppNavHost
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.waddle.navigation.bottom_nav.BottomNavDestination
import com.waddleup.theme.WaddleTheme
import com.waddleup.waddle.presentation.bottom_nav.WaddleBottomNavigation
import com.waddleup.waddle.viewmodel.MainScreenIntent
import com.waddleup.waddle.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.currentUiStateData?.isLoading == true
        }
        enableEdgeToEdge()

        mainViewModel.onIntent(MainScreenIntent.TrySetStartDestination)

        setContent {
            WaddleTheme {
                val navController = rememberNavController()
                val mainScreenState by mainViewModel.uiState.collectAsStateWithLifecycle()
                val showDialog by mainViewModel.showDialog.collectAsStateWithLifecycle(null)
                val showError by mainViewModel.showError.collectAsStateWithLifecycle(null)
                val currentDestination by navController.currentBackStackEntryAsState()
                val shouldShowBottomNav = rememberSaveable (currentDestination) {
                    BottomNavDestination.containsDestination(currentDestination?.destination)
                }

                mainScreenState?.startDestination?.let { startDestination ->
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (shouldShowBottomNav) {
                                WaddleBottomNavigation(
                                    modifier = Modifier.navigationBarsPadding(),
                                    currentDestination = currentDestination?.destination,
                                    onNavigateToDestination = {
                                        navController.navigate(it.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    ) { paddings ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(WaddleTheme.colors.primaryBackground)
                                .padding(paddings)
                        ) {
                            AppNavHost(
                                navController = navController,
                                startDestination = startDestination,
                                onOnboardingCompleted = {
                                    mainViewModel.onIntent(MainScreenIntent.CompleteOnboarding)
                                },
                                onUiEvent = { mainViewModel.dispatchEvent(it, navController) }
                            )

                            showDialog?.let {
                                DialogComponent(
                                    dialogEvent = it,
                                    onDismiss = { mainViewModel.dismissDialog() }
                                )
                            }

                            showError?.let {
                                ErrorComponent(
                                    dialogEvent = it,
                                    onDismiss = { mainViewModel.dismissErrorDialog() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DialogComponent(
    dialogEvent: UiEvent.ShowDialog,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = dialogEvent.title.let { { Text(it) } },
        text = dialogEvent.message.let { { Text(it) } },
        confirmButton = {
            dialogEvent.positiveAction?.let { buttonText ->
                Button(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(buttonText.label)
                }
            }
        },
        dismissButton = {
            dialogEvent.negativeAction?.let { buttonText ->
                OutlinedButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(buttonText.label)
                }
            }
        }
    )
}

@Composable
fun ErrorComponent(
    dialogEvent: UiEvent.ShowError,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Something went wrong") },
        text = { Text(dialogEvent.message) },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Ok")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Ok")
            }
        }
    )
}