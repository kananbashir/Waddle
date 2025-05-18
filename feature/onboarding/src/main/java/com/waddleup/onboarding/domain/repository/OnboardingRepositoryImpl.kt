package com.waddleup.onboarding.domain.repository

import com.waddleup.core.data.datastore.PreferenceDataStore
import com.waddleup.onboarding.data.repository.OnboardingPreferences
import com.waddleup.onboarding.data.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class OnboardingRepositoryImpl(
    private val preferenceDataStore: PreferenceDataStore
): OnboardingRepository {
    override fun hasCompletedOnboarding(): Flow<Boolean> {
        return preferenceDataStore
            .getPreference(OnboardingPreferences.HAS_COMPLETED_ONBOARDING, false)
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        preferenceDataStore.setPreference(OnboardingPreferences.HAS_COMPLETED_ONBOARDING, completed)
    }
}