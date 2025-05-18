package com.waddleup.app.domain

import com.waddleup.core.base.usecase.BaseNoParamUseCase
import com.waddleup.core.base.usecase.Result
import com.waddleup.onboarding.data.repository.OnboardingRepository
import kotlinx.coroutines.flow.first

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class CheckOnboardingStatusUseCase(
    private val onboardingRepository: OnboardingRepository
): BaseNoParamUseCase<Boolean>() {
    override suspend fun execute(): Result<Boolean> {
        return try {
            val flow = onboardingRepository.hasCompletedOnboarding().first()
            Result.Success(flow)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}