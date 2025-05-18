package com.waddleup.onboarding.data.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

interface OnboardingRepository {
    fun hasCompletedOnboarding(): Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)
}