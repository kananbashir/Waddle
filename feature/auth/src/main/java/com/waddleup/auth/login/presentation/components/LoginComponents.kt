package com.waddleup.auth.login.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.waddleup.auth.components.auth_R_drawable
import com.waddleup.auth.components.core_R_drawable
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.input.WaddleTextField
import com.waddleup.core.presentation.components.input.util.Validator

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
internal fun EmailTextField(
    authState: AuthState.LoginState,
    inputValidator: Validator,
    onValueChanged: (text: String) -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.email,
        onValueChange = { text -> onValueChanged(text) },
        titleText = "Email",
        placeholderText = "Email",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
internal fun PasswordTextField(
    authState: AuthState.LoginState,
    inputValidator: Validator,
    onValueChanged: (text: String) -> Unit,
    onTrailingIconClicked: () -> Unit,
    onDone: () -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.password,
        onValueChange = { text ->  onValueChanged(text) },
        titleText = "Password",
        placeholderText = "Password",
        trailingIconRes = if (authState.isPasswordVisible) core_R_drawable.ic_error  else auth_R_drawable.ic_password_invisible,
        onTrailingIconClicked = onTrailingIconClicked,
        visualTransformation = if (authState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        )
    )
}
