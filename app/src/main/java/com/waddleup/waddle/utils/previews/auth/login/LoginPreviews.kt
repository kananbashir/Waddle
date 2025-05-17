package com.waddleup.waddle.utils.previews.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.waddleup.auth.login.presentation.content.LoginContent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.input.util.Validator

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginContent(
        state = AuthState.LoginState(isLoading = true),
        loginValidator = Validator(rememberCoroutineScope()),
        onIntent = {},
        onEvent = {}
    )
}