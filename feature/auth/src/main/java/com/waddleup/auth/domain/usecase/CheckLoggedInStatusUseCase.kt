package com.waddleup.auth.domain.usecase

import com.waddleup.auth.domain.repository.AuthRepository
import com.waddleup.core.base.usecase.BaseNoParamUseCase
import com.waddleup.core.base.usecase.Result
import kotlinx.coroutines.flow.first

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class CheckLoggedInStatusUseCase(
    private val authRepository: AuthRepository
): BaseNoParamUseCase<Boolean>() {
    override suspend fun execute(): Result<Boolean> {
        return try {
            val flow = authRepository.isLoggedIn().first()
            Result.Success(flow)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}