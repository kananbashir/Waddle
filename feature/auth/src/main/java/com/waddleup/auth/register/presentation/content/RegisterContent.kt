package com.waddleup.auth.register.presentation.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.auth.R
import com.waddleup.auth.components.AuthHeader
import com.waddleup.auth.components.FooterAccountLoginOrRegisterText
import com.waddleup.auth.components.LoginWithButtons
import com.waddleup.auth.components.OrDivider
import com.waddleup.auth.register.presentation.components.EmailTextField
import com.waddleup.auth.register.presentation.components.FullNameTextField
import com.waddleup.auth.register.presentation.components.PasswordTextField
import com.waddleup.auth.register.presentation.components.TermsAndConditionsCheckBox
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.core.presentation.components.input.util.Validator
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

private val spacingValue = 12.dp

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    registerValidator: Validator,
    state: AuthState.RegisterState,
    onIntent: (AuthIntent.Register) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WaddleTheme.colors.primaryBackground)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        AuthHeader(
            title = stringResource(id = R.string.register_header_title),
            subtitle = stringResource(id = R.string.register_header_subtitle)
        )

        Spacer(modifier = Modifier.height(spacingValue))

        FullNameTextField(
            authState = state,
            onValueChanged = { onIntent(AuthIntent.Register.FullNameChanged(it)) }
        )

        Spacer(modifier = Modifier.height(spacingValue /2))

        EmailTextField(
            authState = state,
            inputValidator = registerValidator,
            onValueChanged = { onIntent(AuthIntent.Register.EmailChanged(it)) }
        )

        Spacer(modifier = Modifier.height(spacingValue /2))

        PasswordTextField(
            authState = state,
            inputValidator = registerValidator,
            onValueChanged = { onIntent(AuthIntent.Register.PasswordChanged(it)) },
            onTrailingIconClicked = { onIntent(AuthIntent.Register.TogglePasswordVisibility) },
            onDone = { /* TODO: Handle! */ }
        )

        Spacer(modifier = Modifier.height(spacingValue + 2.dp))

        TermsAndConditionsCheckBox(
            authState = state,
            validator = registerValidator,
            onCheckedChange = { onIntent(AuthIntent.Register.ToggleTermsChecked(it)) },
            onTermsClicked = { /* TODO: Handle! */ },
            onConditionsClicked = { /* TODO: Handle! */ }
        )

        Spacer(modifier = Modifier.height((spacingValue * 2) + 6.dp))

        WaddlePrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Register",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(spacingValue * 3))

        OrDivider()

        Spacer(modifier = Modifier.height(spacingValue))

        LoginWithButtons()

        Spacer(modifier = Modifier.height(spacingValue))

        FooterAccountLoginOrRegisterText(
            fullText = R.string.text_already_have_an_account_login,
            clickableText = R.string.text_already_have_an_account_login_clickable,
            clickableTextTag = "register_tag",
            onLinkClicked = { onIntent(AuthIntent.Register.SubmitAlreadyHaveAccount) }
        )
    }
}