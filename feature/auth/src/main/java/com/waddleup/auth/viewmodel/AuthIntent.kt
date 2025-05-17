package com.waddleup.auth.viewmodel

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

sealed class AuthIntent {
    sealed class Login: AuthIntent() {
        data class EmailChanged(val value: String): Login()
        data class PasswordChanged(val value: String): Login()
        data object SubmitLogin: Login()
        data object SubmitForgotPassword: Login()
        data object SubmitDoNotHaveAccount: Login()
        data object TogglePasswordVisibility: Login()
    }

    sealed class Register: AuthIntent() {
        data class FullNameChanged(val value: String): Register()
        data class EmailChanged(val value: String): Register()
        data class PasswordChanged(val value: String): Register()
        data class ToggleTermsChecked(val value: Boolean): Register()
        data object SubmitRegister: Register()
        data object SubmitAlreadyHaveAccount: Register()
        data object TogglePasswordVisibility: Register()
    }

    sealed class PasswordRecovery: AuthIntent() {
        data class EmailChanged(val value: String): PasswordRecovery()
        data class ConfirmationCodeChanged(val value: String): PasswordRecovery()
        data class CreatePasswordChanged(val value: String): PasswordRecovery()
        data class ConfirmPasswordChanged(val value: String): PasswordRecovery()
        data class CurrentPageChanged(val value: Int): PasswordRecovery()
        data object ToggleCreatePasswordVisibility: PasswordRecovery()
        data object ToggleConfirmPasswordVisibility: PasswordRecovery()
        data object SubmitEmailConfirmation: PasswordRecovery()
        data object SubmitConfirmationCode: PasswordRecovery()
        data object SubmitCreateNewPassword: PasswordRecovery()
        data object SubmitGoToLogin: PasswordRecovery()
        data object AbortProcess: PasswordRecovery()
    }
}