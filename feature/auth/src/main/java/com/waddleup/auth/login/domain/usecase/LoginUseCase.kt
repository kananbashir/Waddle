package com.waddleup.auth.login.domain.usecase

import com.waddleup.auth.domain.repository.AuthRepository
import com.waddleup.auth.login.data.dto.LoginDto
import com.waddleup.core.base.usecase.BaseNoParamUseCase
import com.waddleup.core.base.usecase.Result
import kotlinx.coroutines.delay

/**
 * Created on 4/6/2025
 * @author Kanan Bashir
 */

class LoginUseCase(
    private val authRepository: AuthRepository
): BaseNoParamUseCase<LoginDto>() {

    override suspend fun execute(): Result<LoginDto> {
        delay(4000)
//        authRepository.setLoggedInStatus(true)
        return Result.Success(LoginDto())
    }

}