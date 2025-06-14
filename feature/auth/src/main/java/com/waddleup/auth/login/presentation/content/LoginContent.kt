package com.waddleup.auth.login.presentation.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.auth.R
import com.waddleup.auth.components.AuthHeader
import com.waddleup.auth.components.FooterAccountLoginOrRegisterText
import com.waddleup.auth.components.LoginWithButtons
import com.waddleup.auth.components.OrDivider
import com.waddleup.auth.login.presentation.components.EmailTextField
import com.waddleup.auth.login.presentation.components.PasswordTextField
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

private val spacingValue = 12.dp

@Suppress("UNUSED_PARAMETER")
@Composable
fun LoginContent(
    state: AuthState.LoginState,
    onIntent: (AuthIntent.Login) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    WaddleMainContentWrapper(
        isLoading = state.isLoading,
        content = {
            Column {
                AuthHeader(
                    title = stringResource(id = R.string.login_header_title),
                    subtitle = stringResource(id = R.string.login_header_subtitle)
                )

                Spacer(modifier = Modifier.height(spacingValue))

                EmailTextField(
                    authState = state,
                    onValueChanged = { onIntent(AuthIntent.Login.EmailChanged(it)) }
                )

                Spacer(modifier = Modifier.height(spacingValue /2))

                PasswordTextField(
                    authState = state,
                    onValueChanged = {  onIntent(AuthIntent.Login.PasswordChanged(it)) },
                    onTrailingIconClicked = { onIntent(AuthIntent.Login.TogglePasswordVisibility) },
                    onDone = { /* TODO: Handle! */ }
                )

                Spacer(modifier = Modifier.height(spacingValue /2))

                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) { onIntent(AuthIntent.Login.SubmitForgotPassword) },
                    text = "Forgot password?",
                    color = WaddleTheme.colors.buttons.primary,
                    style = WaddleTheme.typography.body2Medium.Poppins
                )

                Spacer(modifier = Modifier.height(spacingValue))

                WaddlePrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = state.allFieldsValid,
                    buttonText = "Log in",
                    onClick = { onIntent(AuthIntent.Login.SubmitLogin) }
                )

                Spacer(modifier = Modifier.height(spacingValue * 3))

                OrDivider()

                Spacer(modifier = Modifier.height(spacingValue))

                LoginWithButtons()

                Spacer(modifier = Modifier.height(spacingValue))

                FooterAccountLoginOrRegisterText(
                    fullText = R.string.text_do_not_have_an_account_register,
                    clickableText = R.string.text_do_not_have_an_account_register_clickable,
                    clickableTextTag = "login_tag",
                    onLinkClicked = { onIntent(AuthIntent.Login.SubmitDoNotHaveAccount) }
                )
            }
        }
    )
}