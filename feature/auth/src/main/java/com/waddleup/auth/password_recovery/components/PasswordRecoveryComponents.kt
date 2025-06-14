package com.waddleup.auth.password_recovery.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.waddleup.auth.components.auth_R_drawable
import com.waddleup.auth.components.core_R_drawable
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.input.WaddleTextField
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = WaddleTheme.typography.body2Regular.Poppins,
        color = WaddleTheme.colors.text.secondary,
        textAlign = textAlign
    )
}

@Composable
fun EmailTextField(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.email,
        onValueChange = { onIntent(AuthIntent.PasswordRecovery.EmailChanged(it)) },
        titleText = "Email",
        placeholderText = "Email",
        errorMessage = authState.emailError,
        isError = authState.emailError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun ConfirmationCodeTextField(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.confirmationCode,
        onValueChange = { onIntent(AuthIntent.PasswordRecovery.ConfirmationCodeChanged(it)) },
        titleText = "Enter confirmation code",
        placeholderText = "Confirmation code",
        errorMessage = authState.confirmationCodeError,
        isError = authState.confirmationCodeError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun CreatePasswordTextField(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit,
    onTrailingIconClicked: () -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.createPassword,
        onValueChange = { onIntent(AuthIntent.PasswordRecovery.CreatePasswordChanged(it)) },
        titleText = "Create password",
        placeholderText = "Create password",
        errorMessage = authState.createPasswordError,
        isError = authState.createPasswordError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIconRes = if (authState.isCreatePasswordVisible) core_R_drawable.ic_error  else auth_R_drawable.ic_password_invisible,
        onTrailingIconClicked = onTrailingIconClicked,
        visualTransformation = if (authState.isCreatePasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ConfirmPasswordTextField(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit,
    onTrailingIconClicked: () -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.confirmPassword,
        onValueChange = { onIntent(AuthIntent.PasswordRecovery.ConfirmPasswordChanged(it)) },
        titleText = "Confirm password",
        placeholderText = "Confirm password",
        errorMessage = authState.confirmPasswordError,
        isError = authState.confirmPasswordError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        trailingIconRes = if (authState.isConfirmPasswordVisible) core_R_drawable.ic_error  else auth_R_drawable.ic_password_invisible,
        onTrailingIconClicked = onTrailingIconClicked,
        visualTransformation = if (authState.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}