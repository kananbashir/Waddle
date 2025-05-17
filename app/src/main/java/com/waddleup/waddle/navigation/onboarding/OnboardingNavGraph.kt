package com.waddleup.waddle.navigation.onboarding

import androidx.navigation.NavGraphBuilder
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
    waddleNavigation<OnboardingDestinations.OnboardingRoot>(OnboardingDestinations.Onboarding) {
        waddleComposable<OnboardingDestinations.Onboarding> {
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