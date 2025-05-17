package com.waddleup.auth.password_recovery.domain.usecase

import com.waddleup.auth.domain.repository.AuthRepository
import com.waddleup.core.base.usecase.BaseUseCase
import com.waddleup.core.base.usecase.Result
import kotlinx.coroutines.delay

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */
class SendOtpToEmailUseCase(
    private val authRepository: AuthRepository
): BaseUseCase<String?, Boolean>() {
    override suspend fun execute(parameters: String?): Result<Boolean> {
        delay( 4000) //Just simulating api call
//        return authRepository.sendOtpToEmail()
        return Result.Success(true)
//        return Result.Error(
//            Exception("An error occurred while sending otp. Please try again later.")
//        )
    }
}