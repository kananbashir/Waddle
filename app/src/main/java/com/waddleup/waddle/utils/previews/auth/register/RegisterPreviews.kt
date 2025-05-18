package com.waddleup.app.utils.previews.auth.register

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.PreviewFontScale
import com.waddleup.auth.register.presentation.content.RegisterContent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.input.util.Validator

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@SuppressLint("UnrememberedMutableState")
@PreviewFontScale
@Composable
private fun RegisterScreenPreview() {
    RegisterContent(
        state = AuthState.RegisterState(),
        registerValidator = Validator(rememberCoroutineScope()),
        onIntent = {},
        onEvent = {}
    )
}