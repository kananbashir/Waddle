package com.waddleup.app.domain

import com.waddleup.core.base.usecase.BaseNoParamUseCase
import com.waddleup.core.base.usecase.Result
import com.waddleup.onboarding.data.repository.OnboardingRepository

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class CompleteOnboardingUseCase(
    private val onboardingRepository: OnboardingRepository
): BaseNoParamUseCase<Unit>() {
    override suspend fun execute(): Result<Unit> {
        return try {
            onboardingRepository.setOnboardingCompleted(true)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}