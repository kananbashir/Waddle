package com.waddleup.auth.login.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.waddleup.app.theme.R
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.input.WaddleMainTextField
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
internal fun EmailTextField(
    authState: AuthState.LoginState,
    onValueChanged: (text: String) -> Unit
) {
    WaddleMainTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.email,
        onValueChange = { text -> onValueChanged(text) },
        titleText = stringResource(R.string.text_email),
        placeholderText = stringResource(R.string.text_email),
        errorMessage = authState.emailError,
        isError = authState.emailError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
internal fun PasswordTextField(
    authState: AuthState.LoginState,
    onValueChanged: (text: String) -> Unit,
    onTrailingIconClicked: () -> Unit,
    onDone: () -> Unit
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(true) }

    WaddleMainTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.password,
        onValueChange = { text ->  onValueChanged(text) },
        titleText = stringResource(R.string.text_password),
        placeholderText = stringResource(R.string.text_password),
        errorMessage = authState.passwordError,
        isError = authState.passwordError.isNullOrBlank().not(),
        trailingIcon = {
            AnimatedContent(
                targetState = isPasswordVisible,
                label = ""
            ) {_ ->
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            isPasswordVisible = !isPasswordVisible
                            onTrailingIconClicked()
                        },
                    painter = painterResource(
                        if (authState.isPasswordVisible) R.drawable.ic_error  else R.drawable.ic_password_invisible
                    ),
                    contentDescription = null,
                    tint = WaddleTheme.colors.run {
                        if (authState.passwordError.isNullOrBlank()) inputFields.primaryText
                        else inputFields.primaryError
                    }
                )
            }
        },
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
