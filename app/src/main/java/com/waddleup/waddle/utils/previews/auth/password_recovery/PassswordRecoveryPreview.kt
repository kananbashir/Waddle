package com.waddleup.waddle.utils.previews.auth.password_recovery

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.waddleup.auth.password_recovery.content.PasswordRecoveryContent
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoveryFourthPage
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoverySecondPage
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoveryThirdPage
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@SuppressLint("UnrememberedMutableState")
//@Preview
@Composable
private fun PasswordRecoveryPreview() {
    PasswordRecoveryContent(
        state = AuthState.PasswordRecoveryState(),
        onIntent = {},
        onEvent = {}
    )
}

//@Preview
@Composable
private fun PasswordRecoverySecondPagePreview() {
    Box(
        modifier = Modifier
            .background(color = WaddleTheme.colors.primaryBackground)
    ) {
        PasswordRecoverySecondPage(
            authState = AuthState.PasswordRecoveryState(),
            onIntent = {},
            onWriteToUsClicked = {}
        )
    }
}

@Preview
@Composable
private fun PasswordRecoveryThirdPagePreview() {
    Box(
        modifier = Modifier
            .background(color = WaddleTheme.colors.primaryBackground)
    ) {
        PasswordRecoveryThirdPage(
            authState = AuthState.PasswordRecoveryState(),
            onIntent = {},
            onWriteToUsClicked = {}
        )
    }
}

@Preview
@Composable
private fun PasswordRecoveryFourthPagePreview() {
    Box(
        modifier = Modifier
            .background(color = WaddleTheme.colors.primaryBackground)
    ) {
        PasswordRecoveryFourthPage()
    }
}