package com.waddleup.auth.viewmodel

import androidx.compose.runtime.Immutable
import com.waddleup.auth.login.data.dto.LoginDto

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Immutable
data class AuthState(
    val loginState: LoginState = LoginState(),
    val registerState: RegisterState = RegisterState(),
    val passwordRecoveryState: PasswordRecoveryState = PasswordRecoveryState()
) {

    @Immutable
    data class LoginState(
        val email: String = "",
        val password: String = "",
        val loginDto: LoginDto? = null,
        val isLoggedIn: Boolean = false,
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val isValid: Boolean = false
    )

    @Immutable
    data class RegisterState(
        val fullName: String = "",
        val email: String = "",
        val password: String = "",
        val loginDto: LoginDto? = null,
        val isLoggedIn: Boolean = false,
        val isPasswordVisible: Boolean = false,
        val isCheckedTerms: Boolean = false,
        val isLoading: Boolean = false,
        val isValid: Boolean = false,
    )

    @Immutable
    data class PasswordRecoveryState(
        val email: String = "",
        val confirmationCode: String = "",
        val createPassword: String = "",
        val confirmPassword: String = "",
        val isCreatePasswordVisible: Boolean = false,
        val isConfirmPasswordVisible: Boolean = false,
        val otpSent: Boolean = false,
        val currentPage: Int = 0,
        val isLoading: Boolean = false,
        val isValid: Boolean = false
    )
}