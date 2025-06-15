package com.waddleup.auth.password_recovery.content.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.auth.password_recovery.components.EmailTextField
import com.waddleup.auth.password_recovery.components.HeaderText
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
fun PasswordRecoveryFirstPage(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        HeaderText(
            text = stringResource(id = R.string.password_recovery_header_info)
        )

        Spacer(modifier = Modifier.height(24.dp))

        EmailTextField(
            authState = authState,
            onIntent = onIntent
        )
    }
}