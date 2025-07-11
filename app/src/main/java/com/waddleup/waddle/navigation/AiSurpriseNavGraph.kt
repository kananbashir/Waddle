package com.waddleup.waddle.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.navigation.ai_surprise.AiSurpriseDestination
import com.waddleup.navigation.ai_surprise.AiSurpriseRootDestination
import com.waddleup.waddle.navigation.util.waddleComposable
import com.waddleup.waddle.navigation.util.waddleNavigation

/**
 * Created on 5/10/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.aiSurpriseNavGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    waddleNavigation<AiSurpriseRootDestination>(AiSurpriseDestination) {
        waddleComposable<AiSurpriseDestination>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Welcome AiSurprise!")
            }
        }
    }
}