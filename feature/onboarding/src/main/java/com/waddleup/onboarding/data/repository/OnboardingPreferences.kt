package com.waddleup.onboarding.data.repository

import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */


object OnboardingPreferences {
    val HAS_COMPLETED_ONBOARDING = booleanPreferencesKey("has_completed_onboarding")
}