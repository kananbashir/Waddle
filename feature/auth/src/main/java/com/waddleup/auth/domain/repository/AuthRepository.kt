package com.waddleup.auth.domain.repository

import com.waddleup.auth.login.data.dto.LoginDto
import com.waddleup.core.base.usecase.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

interface AuthRepository {
    suspend fun login(): Result<LoginDto>
    suspend fun sendOtpToEmail(): Result<Boolean>
    suspend fun confirmOtp(): Result<Boolean>
    suspend fun updatePassword(): Result<Boolean>

    fun isLoggedIn(): Flow<Boolean>
    suspend fun setLoggedInStatus(isLoggedIn: Boolean)
}