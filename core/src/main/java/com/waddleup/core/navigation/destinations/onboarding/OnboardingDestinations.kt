package com.waddleup.core.navigation.destinations.onboarding

import kotlinx.serialization.Serializable

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Serializable
sealed class OnboardingDestinations {
    @Serializable data object OnboardingRoot: OnboardingDestinations()
    @Serializable data object Onboarding: OnboardingDestinations()
}