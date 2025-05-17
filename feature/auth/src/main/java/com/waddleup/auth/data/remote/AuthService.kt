package com.waddleup.auth.data.remote

import com.waddleup.auth.login.data.dto.LoginDto
import retrofit2.http.POST

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

interface AuthService {

    @POST("")
    suspend fun login(): LoginDto

    @POST("")
    suspend fun sendOtpToEmail(): Boolean

    @POST("")
    suspend fun confirmOtp(): Boolean

    @POST("")
    suspend fun updatePassword(): Boolean
}