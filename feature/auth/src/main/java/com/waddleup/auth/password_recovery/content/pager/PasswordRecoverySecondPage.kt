package com.waddleup.auth.password_recovery.content.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.auth.password_recovery.components.ConfirmationCodeTextField
import com.waddleup.auth.password_recovery.components.HeaderText
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.text.WaddleAutoResizedText
import com.waddleup.core.presentation.components.text.WaddleClickableText
import com.waddleup.core.presentation.components.text.util.ClickableTextParams
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
fun PasswordRecoverySecondPage(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit,
    onWriteToUsClicked: () -> Unit
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            HeaderText(
                text = stringResource(
                    id = R.string.password_recovery_header_info_mail_sent,
                    authState.email
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            ConfirmationCodeTextField(
                authState = authState,
                onIntent = onIntent
            )
        }

        WaddleClickableText(
            fullText = stringResource(id = R.string.text_do_not_receive_otp),
            links = listOf(
                ClickableTextParams(
                    tag = "write_to_us_tag",
                    text = stringResource(id = R.string.text_do_not_receive_otp_clickable),
                    color = WaddleTheme.colors.text.quinary,
                    style = WaddleTheme.typography.body2Bold.Poppins.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    linkInteractionListener = { onWriteToUsClicked() }
                )
            ),
            textContent = { annotatedText ->
                WaddleAutoResizedText(
                    modifier = Modifier.padding(bottom = 22.dp),
                    text = annotatedText,
                    style = WaddleTheme.typography.body2Regular.Poppins,
                    color = WaddleTheme.colors.text.secondary
                )
            }
        )
    }
}