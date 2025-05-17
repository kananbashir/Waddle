package com.waddleup.auth.viewmodel

import com.waddleup.auth.login.domain.usecase.LoginUseCase
import com.waddleup.auth.password_recovery.domain.usecase.ConfirmOtpUseCase
import com.waddleup.auth.password_recovery.domain.usecase.SendOtpToEmailUseCase
import com.waddleup.auth.password_recovery.domain.usecase.UpdateAccountPasswordUseCase
import com.waddleup.auth.viewmodel.AuthState.LoginState
import com.waddleup.auth.viewmodel.AuthState.PasswordRecoveryState
import com.waddleup.auth.viewmodel.AuthState.RegisterState
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.navigation.destinations.auth.AuthDestinations
import com.waddleup.core.navigation.destinations.home.HomeDestinations
import com.waddleup.core.presentation.components.input.util.Validator

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class AuthViewModel(
    dispatchersProvider: DispatchersProvider,
    private val loginUseCase: LoginUseCase,
    private val sendOtpToEmailUseCase: SendOtpToEmailUseCase,
    private val confirmOtpUseCase: ConfirmOtpUseCase,
    private val updateAccountPasswordUseCase: UpdateAccountPasswordUseCase,
) : BaseViewModel<AuthState, AuthIntent>(dispatchersProvider) {
    override val initialState: AuthState
        get() = AuthState()

    val loginValidator = Validator(safeCoroutineScope)
    val registerValidator = Validator(safeCoroutineScope)
    val passwordRecoveryValidator = Validator(safeCoroutineScope)

    override fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.Login -> handleLoginIntent(intent)
            is AuthIntent.Register -> handleRegisterIntent(intent)
            is AuthIntent.PasswordRecovery -> handlePasswordRecoveryIntent(intent)
        }
    }

    private fun handleLoginIntent(intent: AuthIntent.Login) {
        when (intent) {
            is AuthIntent.Login.EmailChanged -> updateLoginState { copy(email = intent.value) }
            is AuthIntent.Login.PasswordChanged -> updateLoginState { copy(password = intent.value) }
            AuthIntent.Login.SubmitForgotPassword -> sendEvent(UiEvent.Navigate(AuthDestinations.PasswordRecovery))
            AuthIntent.Login.SubmitLogin -> login()
            AuthIntent.Login.SubmitDoNotHaveAccount -> sendEvent(UiEvent.Navigate(AuthDestinations.Register))
            AuthIntent.Login.TogglePasswordVisibility -> updateLoginState { copy(isPasswordVisible = !isPasswordVisible) }
        }
    }

    private fun handleRegisterIntent(intent: AuthIntent.Register) {
        when (intent) {
            is AuthIntent.Register.EmailChanged -> updateRegisterState { copy(email = intent.value) }
            is AuthIntent.Register.FullNameChanged -> updateRegisterState { copy(fullName = intent.value) }
            is AuthIntent.Register.PasswordChanged -> updateRegisterState { copy(password = intent.value) }
            is AuthIntent.Register.ToggleTermsChecked -> updateRegisterState { copy(isCheckedTerms = intent.value) }
            AuthIntent.Register.SubmitRegister -> { /* Register */ }
            AuthIntent.Register.SubmitAlreadyHaveAccount -> sendEvent(UiEvent.NavigateBack)
            AuthIntent.Register.TogglePasswordVisibility -> updateRegisterState { copy(isPasswordVisible = !isPasswordVisible) }
        }
    }

    private fun handlePasswordRecoveryIntent(intent: AuthIntent.PasswordRecovery) {
        when (intent) {
            is AuthIntent.PasswordRecovery.ConfirmPasswordChanged ->
                updatePasswordRecoveryState { copy(confirmPassword = intent.value) }

            is AuthIntent.PasswordRecovery.CreatePasswordChanged ->
                updatePasswordRecoveryState { copy(createPassword = intent.value) }

            is AuthIntent.PasswordRecovery.ConfirmationCodeChanged ->
                updatePasswordRecoveryState { copy(confirmationCode = intent.value) }

            is AuthIntent.PasswordRecovery.CurrentPageChanged ->
                updatePasswordRecoveryState { copy(currentPage = intent.value) }

            is AuthIntent.PasswordRecovery.EmailChanged ->
                updatePasswordRecoveryState { copy(email = intent.value) }

            AuthIntent.PasswordRecovery.SubmitEmailConfirmation -> sendOtpToEmail()
            AuthIntent.PasswordRecovery.SubmitConfirmationCode -> confirmOtp()
            AuthIntent.PasswordRecovery.SubmitCreateNewPassword -> updatePassword()
            AuthIntent.PasswordRecovery.SubmitGoToLogin,
            AuthIntent.PasswordRecovery.AbortProcess-> navigateBackAndReset()

            AuthIntent.PasswordRecovery.ToggleConfirmPasswordVisibility ->
                updatePasswordRecoveryState { copy(isConfirmPasswordVisible = !isConfirmPasswordVisible) }

            AuthIntent.PasswordRecovery.ToggleCreatePasswordVisibility ->
                updatePasswordRecoveryState { copy(isCreatePasswordVisible = !isCreatePasswordVisible) }
        }
    }

    private fun updateLoginState(block: LoginState.() -> LoginState) =
        setState { it?.copy(loginState = it.loginState.block()) }

    private fun updateRegisterState(block: RegisterState.() -> RegisterState) =
        setState { it?.copy(registerState = it.registerState.block()) }


    private fun updatePasswordRecoveryState(block: PasswordRecoveryState.() -> PasswordRecoveryState) =
        setState { it?.copy(passwordRecoveryState = it.passwordRecoveryState.block()) }

    private fun login() {
        execute(
            useCase = loginUseCase,
            onStart = { updateLoginState { copy(isLoading = true) } },
            onSuccess = {
//                updateLoginState { copy(loginDto = it) }
                sendEvent(
                    UiEvent.Navigate(
                        route = HomeDestinations.HomeRoot(someData = "some dataa"),
                        popUpTo = AuthDestinations.AuthRoot
                    )
                )
            },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) }
        )
    }

    private fun sendOtpToEmail() {
        execute(
            useCase = sendOtpToEmailUseCase,
            params = currentUiStateData?.passwordRecoveryState?.email,
            onSuccess = { incrementCurrentPage() },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) }
        )
    }

    private fun confirmOtp() {
        execute(
            useCase = confirmOtpUseCase,
            params = currentUiStateData?.passwordRecoveryState?.confirmationCode,
            onSuccess = { incrementCurrentPage() },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) }
        )
    }

    private fun updatePassword() {
        execute(
            useCase = updateAccountPasswordUseCase,
            params = currentUiStateData?.passwordRecoveryState?.confirmPassword,
            onSuccess = {
                incrementCurrentPage()
            },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) }
        )
    }

    private fun navigateBackAndReset() {
        passwordRecoveryValidator.clear()
        updatePasswordRecoveryState { PasswordRecoveryState() }
        sendEvent(UiEvent.NavigateBack)
    }

    private fun incrementCurrentPage() {
        currentUiStateData?.let {
            onIntent(
                AuthIntent.PasswordRecovery.CurrentPageChanged(
                    (it.passwordRecoveryState.currentPage + 1).coerceAtMost(4)
                )
            )
        }
    }
}