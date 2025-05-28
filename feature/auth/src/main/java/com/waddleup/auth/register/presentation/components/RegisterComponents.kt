package com.waddleup.auth.register.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.waddleup.auth.R
import com.waddleup.auth.components.auth_R_drawable
import com.waddleup.auth.components.core_R_drawable
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.presentation.components.checkbox.WaddleCheckBox
import com.waddleup.core.presentation.components.input.WaddleTextField
import com.waddleup.core.presentation.components.text.WaddleClickableText
import com.waddleup.core.presentation.components.text.util.ClickableTextParams
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
internal fun FullNameTextField(
    authState: AuthState.RegisterState,
    onValueChanged: (text: String) -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.fullName,
        onValueChange = { text -> onValueChanged(text) },
        titleText = "Full name",
        placeholderText = "Full name",
        errorMessage = authState.fullNameError,
        isError = authState.fullNameError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
internal fun EmailTextField(
    authState: AuthState.RegisterState,
    onValueChanged: (text: String) -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.email,
        onValueChange = { text -> onValueChanged(text) },
        titleText = "Email",
        placeholderText = "Email",
        errorMessage = authState.emailError,
        isError = authState.emailError.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
internal fun PasswordTextField(
    authState: AuthState.RegisterState,
    onValueChanged: (text: String) -> Unit,
    onTrailingIconClicked: () -> Unit,
    onDone: () -> Unit
) {
    WaddleTextField(
        modifier = Modifier.fillMaxWidth(),
        value = authState.password,
        onValueChange = { text ->  onValueChanged(text) },
        titleText = "Password",
        placeholderText = "Password",
        errorMessage = authState.passwordError,
        isError = authState.passwordError.isNullOrBlank().not(),
        trailingIconRes = if (authState.isPasswordVisible) core_R_drawable.ic_error  else auth_R_drawable.ic_password_invisible,
        onTrailingIconClicked = onTrailingIconClicked,
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

@Composable
internal fun TermsAndConditionsCheckBox(
    modifier: Modifier = Modifier,
    authState: AuthState.RegisterState,
    onCheckedChange: (value: Boolean) -> Unit,
    onTermsClicked: () -> Unit,
    onConditionsClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        WaddleCheckBox(
            isChecked = authState.isCheckedTerms,
            isError = authState.isCheckedTerms.not(),
            onValueChange = { value -> onCheckedChange(value) },
        )

        WaddleClickableText(
            fullText = stringResource(id = R.string.checkbox_text_terms_and_conditions),
            textColor = WaddleTheme.colors.primaryText,
            textStyle = WaddleTheme.typography.body2Regular.Poppins,
            links = listOf(
                ClickableTextParams(
                    tag = "terms_tag",
                    text = stringResource(id = R.string.checkbox_text_terms_and_conditions_clickable_1),
                    color = WaddleTheme.colors.primaryText,
                    style = WaddleTheme.typography.body2Bold.Poppins,
                    linkInteractionListener = { onTermsClicked() }
                ),
                ClickableTextParams(
                    tag = "conditions_tag",
                    text = stringResource(id = R.string.checkbox_text_terms_and_conditions_clickable_2),
                    color = WaddleTheme.colors.primaryText,
                    style = WaddleTheme.typography.body2Bold.Poppins,
                    linkInteractionListener = { onConditionsClicked() }
                )
            )
        )
    }
}