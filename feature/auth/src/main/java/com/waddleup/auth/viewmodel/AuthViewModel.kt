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
import com.waddleup.core.presentation.components.input.util.ValidatorParam.ValidationConditionParams
import com.waddleup.core.presentation.components.input.util.ValidatorParam.ValidationRegexParams
import com.waddleup.core.util.Regexes
import com.waddleup.navigation.auth.AuthDestinations
import com.waddleup.navigation.home.HomeDestinations

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

    override fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.Login -> handleLoginIntent(intent)
            is AuthIntent.Register -> handleRegisterIntent(intent)
            is AuthIntent.PasswordRecovery -> handlePasswordRecoveryIntent(intent)
        }
    }

    private fun handleLoginIntent(intent: AuthIntent.Login) {
        when (intent) {
            is AuthIntent.Login.EmailChanged -> updateLoginEmail(intent.value)
            is AuthIntent.Login.PasswordChanged -> updateLoginPassword(intent.value)
            AuthIntent.Login.SubmitForgotPassword -> sendEvent(UiEvent.Navigate(AuthDestinations.PasswordRecovery))
            AuthIntent.Login.SubmitLogin -> login()
            AuthIntent.Login.SubmitDoNotHaveAccount -> sendEvent(UiEvent.Navigate(AuthDestinations.Register))
            AuthIntent.Login.TogglePasswordVisibility -> updateLoginState { copy(isPasswordVisible = !isPasswordVisible) }
        }
    }

    private fun handleRegisterIntent(intent: AuthIntent.Register) {
        when (intent) {
            is AuthIntent.Register.EmailChanged -> updateRegisterEmail(intent.value)
            is AuthIntent.Register.FullNameChanged -> updateRegisterFullName(intent.value)
            is AuthIntent.Register.PasswordChanged -> updateRegisterPassword(intent.value)
            is AuthIntent.Register.ToggleTermsChecked -> updateRegisterState { copy(isCheckedTerms = intent.value) }
            AuthIntent.Register.SubmitRegister -> { /* Register */ }
            AuthIntent.Register.SubmitAlreadyHaveAccount -> sendEvent(UiEvent.NavigateBack)
            AuthIntent.Register.TogglePasswordVisibility -> updateRegisterState { copy(isPasswordVisible = !isPasswordVisible) }
        }
    }

    private fun handlePasswordRecoveryIntent(intent: AuthIntent.PasswordRecovery) {
        when (intent) {
            is AuthIntent.PasswordRecovery.ConfirmPasswordChanged -> updatePassRecoveryConfirmPass(intent.value)
            is AuthIntent.PasswordRecovery.CreatePasswordChanged -> updatePassRecoveryCreatePass(intent.value)
            is AuthIntent.PasswordRecovery.ConfirmationCodeChanged -> updatePassRecoveryConfCode(intent.value)
            is AuthIntent.PasswordRecovery.CurrentPageChanged -> updatePassRecoveryState { copy(currentPage = intent.value) }
            is AuthIntent.PasswordRecovery.EmailChanged -> updatePassRecoveryEmail(intent.value)

            AuthIntent.PasswordRecovery.SubmitEmailConfirmation -> sendOtpToEmail()
            AuthIntent.PasswordRecovery.SubmitConfirmationCode -> confirmOtp()
            AuthIntent.PasswordRecovery.SubmitCreateNewPassword -> updatePassword()
            AuthIntent.PasswordRecovery.SubmitGoToLogin,
            AuthIntent.PasswordRecovery.AbortProcess-> navigateBackAndReset()

            AuthIntent.PasswordRecovery.ToggleConfirmPasswordVisibility ->
                updatePassRecoveryState { copy(isConfirmPasswordVisible = !isConfirmPasswordVisible) }

            AuthIntent.PasswordRecovery.ToggleCreatePasswordVisibility ->
                updatePassRecoveryState { copy(isCreatePasswordVisible = !isCreatePasswordVisible) }
        }
    }

    private fun updateLoginEmail(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "Should not contain white spaces"
            ),
            ValidationRegexParams(
                regexString = Regexes.NOT_EMPTY
            ),
            ValidationRegexParams(
                regexString = Regexes.VALID_EMAIL,
                errorMessage = "Invalid email address"
            )
        ) { errorMessage ->
            updateLoginState { copy(email = value, emailError = errorMessage) }
        }
    }

    private fun updateLoginPassword(value: String) {
        validate(
            value,
            ValidationRegexParams(regexString = Regexes.NOT_EMPTY),
            ValidationRegexParams(
                regexString = Regexes.minChars(8),
                errorMessage = "At least 8 character needed"
            )
        ) { errorMessage ->
            updateLoginState { copy(password = value, passwordError = errorMessage) }
        }
    }

    private fun updateRegisterFullName(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "No white space in name"
            ),
            ValidationRegexParams(
                regexString = Regexes.NOT_EMPTY
            )
        ) { errorMessage ->
            updateRegisterState { copy(fullName = value, fullNameError = errorMessage) }
        }
    }

    private fun updateRegisterEmail(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "Should not contain white spaces"
            ),
            ValidationRegexParams(
                regexString = Regexes.NOT_EMPTY
            ),
            ValidationRegexParams(
                regexString = Regexes.VALID_EMAIL,
                errorMessage = "Invalid email address"
            )
        ) { errorMessage ->
            updateRegisterState { copy(email = value, emailError = errorMessage) }
        }
    }

    private fun updateRegisterPassword(value: String) {
        validate(
            value,
            ValidationRegexParams(regexString = Regexes.NOT_EMPTY),
            ValidationRegexParams(
                regexString = Regexes.minChars(8),
                errorMessage = "At least 8 character needed"
            )
        ) { errorMessage ->
            updateRegisterState { copy(password = value, passwordError = errorMessage) }
        }
    }

    private fun updatePassRecoveryEmail(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "Should not contain white spaces"
            ),
            ValidationRegexParams(Regexes.NOT_EMPTY),
            ValidationRegexParams(
                regexString = Regexes.VALID_EMAIL,
                errorMessage = "Invalid email address"
            )
        ) { errorMessage ->
            updatePassRecoveryState { copy(email = value, emailError = errorMessage) }
        }
    }

    private fun updatePassRecoveryConfCode(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "Should not contain white spaces"
            ),
            ValidationRegexParams(Regexes.NOT_EMPTY)
        ) { errorMessage ->
            updatePassRecoveryState { copy(confirmationCode = value, confirmationCodeError = errorMessage) }
        }
    }

    private fun updatePassRecoveryCreatePass(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "Should not contain white spaces"
            ),
            ValidationRegexParams(Regexes.NOT_EMPTY),
            ValidationRegexParams(Regexes.MAIN_PASSWORD),
            ValidationRegexParams(
                regexString = Regexes.minChars(8),
                errorMessage = "At least 8 characters needed"
            )
        ) { errorMessage ->
            updatePassRecoveryState { copy(createPassword = value, createPasswordError = errorMessage) }
        }
    }

    private fun updatePassRecoveryConfirmPass(value: String) {
        validate(
            value,
            ValidationRegexParams(
                regexString = Regexes.NO_WHITE_SPACE,
                errorMessage = "Should not contain white spaces"
            ),
            ValidationRegexParams(Regexes.NOT_EMPTY),
            ValidationRegexParams(Regexes.MAIN_PASSWORD),
            ValidationRegexParams(
                regexString = Regexes.minChars(8),
                errorMessage = "At least 8 characters needed"
            ),
            ValidationConditionParams(
                condition = { value == uiState.value.passwordRecoveryState.createPassword },
                errorMessage = "Passwords doesn't match"
            )
        ) { errorMessage ->
            updatePassRecoveryState { copy(confirmPassword = value, confirmPasswordError = errorMessage) }
        }
    }


    private fun updateLoginState(block: LoginState.() -> LoginState) =
        setState { it.copy(loginState = it.loginState.block()) }

    private fun updateRegisterState(block: RegisterState.() -> RegisterState) =
        setState { it.copy(registerState = it.registerState.block()) }

    private fun updatePassRecoveryState(block: PasswordRecoveryState.() -> PasswordRecoveryState) =
        setState { it.copy(passwordRecoveryState = it.passwordRecoveryState.block()) }

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
            onStart = { updatePassRecoveryState { copy(isLoading = true) } },
            onSuccess = { incrementCurrentPage() },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) },
            onCompleted = { updatePassRecoveryState { copy(isLoading = false) } }
        )
    }

    private fun confirmOtp() {
        execute(
            useCase = confirmOtpUseCase,
            params = currentUiStateData?.passwordRecoveryState?.confirmationCode,
            onStart = { updatePassRecoveryState { copy(isLoading = true) } },
            onSuccess = { incrementCurrentPage() },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) },
            onCompleted = { updatePassRecoveryState { copy(isLoading = false) } }
        )
    }

    private fun updatePassword() {
        execute(
            useCase = updateAccountPasswordUseCase,
            params = currentUiStateData?.passwordRecoveryState?.confirmPassword,
            onStart = { updatePassRecoveryState { copy(isLoading = true) } },
            onSuccess = {
                incrementCurrentPage()
            },
            onError = { msg, _ -> sendEvent(UiEvent.ShowError(msg)) },
            onCompleted = { updatePassRecoveryState { copy(isLoading = false) } }
        )
    }

    private fun navigateBackAndReset() {
        sendEvent(UiEvent.NavigateBack)
        updatePassRecoveryState { PasswordRecoveryState() }
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