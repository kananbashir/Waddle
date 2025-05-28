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
        val emailError: String? = "",
        val password: String = "",
        val passwordError: String? = "",
        val loginDto: LoginDto? = null,
        val isLoggedIn: Boolean = false,
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
    ) {
        val allFieldsValid: Boolean
            get() = (emailError == null
                    && passwordError == null)
    }

    @Immutable
    data class RegisterState(
        val fullName: String = "",
        val fullNameError: String? = "",
        val email: String = "",
        val emailError: String? = "",
        val password: String = "",
        val passwordError: String? = "",
        val loginDto: LoginDto? = null,
        val isLoggedIn: Boolean = false,
        val isPasswordVisible: Boolean = false,
        val isCheckedTerms: Boolean = false,
        val isLoading: Boolean = false,
    ) {
        val allFieldsValid: Boolean
            get() = (fullNameError == null
                    && emailError == null
                    && passwordError == null
                    && isCheckedTerms)
    }

    @Immutable
    data class PasswordRecoveryState(
        val email: String = "",
        val emailError: String? = "",
        val confirmationCode: String = "",
        val confirmationCodeError: String? = "",
        val createPassword: String = "",
        val createPasswordError: String? = "",
        val confirmPassword: String = "",
        val confirmPasswordError: String? = "",
        val isCreatePasswordVisible: Boolean = false,
        val isConfirmPasswordVisible: Boolean = false,
        val otpSent: Boolean = false,
        val currentPage: Int = 0,
        val isLoading: Boolean = false,
    ) {
        val isEmailValid: Boolean
            get() = emailError == null
        val isConfirmationCodeValid: Boolean
            get() = confirmationCodeError == null
        val arePasswordsValid: Boolean
            get() = (createPasswordError == null
                    && confirmPasswordError == null)
    }
}