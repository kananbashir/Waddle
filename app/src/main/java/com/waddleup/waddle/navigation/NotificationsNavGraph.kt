package com.waddleup.waddle.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.navigation.notifications.NotificationsDestinations
import com.waddleup.waddle.navigation.util.popEnterFromBottomAnim
import com.waddleup.waddle.navigation.util.popExitToTopAnim
import com.waddleup.waddle.navigation.util.slideInFromTopAnim
import com.waddleup.waddle.navigation.util.slideOutToBottomAnim
import com.waddleup.waddle.navigation.util.waddleComposable

/**
 * Created on 6/1/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.notificationNavGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    navigation<NotificationsDestinations.NotificationsRoot>(NotificationsDestinations.Notifications) {
        waddleComposable<NotificationsDestinations.Notifications>(
            enterTransition = { slideInFromTopAnim },
            exitTransition = { slideOutToBottomAnim },
            popEnterTransition = { popEnterFromBottomAnim },
            popExitTransition = { popExitToTopAnim }
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Welcome to Notifications")
            }
        }
    }
}