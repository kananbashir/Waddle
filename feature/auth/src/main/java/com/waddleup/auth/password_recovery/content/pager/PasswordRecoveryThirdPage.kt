package com.waddleup.auth.password_recovery.content.pager

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.waddleup.auth.R
import com.waddleup.auth.password_recovery.components.ConfirmPasswordTextField
import com.waddleup.auth.password_recovery.components.CreatePasswordTextField
import com.waddleup.auth.password_recovery.components.HeaderText
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.theme.WaddleTheme

/**
 * Created on 4/28/2025
 * @author Kanan Bashir
 */

@Composable
fun PasswordRecoveryThirdPage(
    authState: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit,
    onWriteToUsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.password_recovery_third_header_title),
            style = WaddleTheme.typography.headline2Medium.Poppins,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        HeaderText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                id = R.string.password_recovery_third_header_subtitle,
                authState.email
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        CreatePasswordTextField(
            authState = authState,
            onIntent = onIntent,
            onTrailingIconClicked = { onIntent(AuthIntent.PasswordRecovery.ToggleCreatePasswordVisibility) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ConfirmPasswordTextField(
            authState = authState,
            onIntent = onIntent,
            onTrailingIconClicked = { onIntent(AuthIntent.PasswordRecovery.ToggleConfirmPasswordVisibility) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "The password must contain:",
            style = WaddleTheme.typography.body2Regular.Poppins,
            color = WaddleTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordCompatibilityRule.entries.forEach {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_mark_circle),
                    contentDescription = "Password compatibility checkmark icon"
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(id = it.text),
                    style = WaddleTheme.typography.body2Regular.Poppins,
                    color = WaddleTheme.colors.primaryText
                )
            }
        }
    }
}

enum class PasswordCompatibilityRule(@StringRes val text: Int) {
    AT_LEAST_ONE_LETTER(R.string.password_requirement_letter),
    AT_LEAST_ONE_DIGIT(R.string.password_requirement_digit),
    MIN_8_CHARS(R.string.password_requirement_min_8_chars)
}