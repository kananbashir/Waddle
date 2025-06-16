package com.waddleup.waddle.navigation

import androidx.navigation.NavGraphBuilder
import com.waddleup.navigation.onboarding.OnboardingDestination
import com.waddleup.navigation.onboarding.OnboardingRootDestination
import com.waddleup.waddle.navigation.util.waddleComposable
import com.waddleup.waddle.navigation.util.waddleNavigation
import com.waddleup.onboarding.presentation.components.OnboardingScreen
import com.waddleup.onboarding.presentation.model.OnboardingModel

/**
 * Created on 4/12/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.onboardingNavGraph(
    onOnboardingCompleted: () -> Unit
) {
    waddleNavigation<OnboardingRootDestination>(OnboardingDestination) {
        waddleComposable<OnboardingDestination> {
            OnboardingScreen(
                pages = listOf(
                    OnboardingModel.FirstPage,
                    OnboardingModel.SecondPage,
                    OnboardingModel.ThirdPage
                ),
                onOnboardingCompleted = onOnboardingCompleted
            )
        }
    }
}