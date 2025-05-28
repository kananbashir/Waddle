package com.waddleup.waddle.utils.previews.auth.register

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewFontScale
import com.waddleup.auth.register.presentation.content.RegisterContent
import com.waddleup.auth.viewmodel.AuthState

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
        onIntent = {},
        onEvent = {}
    )
}