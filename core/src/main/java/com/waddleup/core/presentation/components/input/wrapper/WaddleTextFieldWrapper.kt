package com.waddleup.core.presentation.components.input.wrapper

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.input.util.mainWaddleColors
import com.waddleup.core.presentation.components.input.wrapper.component.AnimatedErrorMessage
import com.waddleup.core.presentation.components.input.wrapper.component.WaddleTextFieldDecorator
import com.waddleup.core.presentation.components.input.wrapper.component.getTrailingIcon
import com.waddleup.core.presentation.components.input.wrapper.component.getUpdatedValue
import com.waddleup.core.presentation.util.applyIf
import com.waddleup.core.presentation.util.checkValues
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
internal fun WaddleTextFieldWrapper(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = WaddleTheme.typography.body1Medium.PlusJakarta,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = WaddleTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.mainWaddleColors(),
    @DrawableRes leadingIconRes: Int? = null,
    @DrawableRes trailingIconRes: Int? = null,
    unspecifiedLeadingIconColor: Boolean = false,
    unspecifiedTrailingIconColor: Boolean = false,
    onLeadingIconClicked: (() -> Unit)? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    placeholderText: String? = null,
    prefixText: String? = null,
    suffixText: String? = null,
    errorMessage: String? = null,
    titleText: String? = null,
    animatedPlaceholder: Boolean,
) {
    rememberSaveable { checkValues(prefix, prefixText, onBothNull = {}) }
    rememberSaveable { checkValues(suffix, suffixText, onBothNull = {}) }
    rememberSaveable { checkValues(placeholder, placeholderText, onBothNull = {}) }
    rememberSaveable { checkValues(leadingIcon, leadingIconRes, onBothNull = {}) }
    rememberSaveable { checkValues(trailingIcon, trailingIconRes, onBothNull = {}) }
    rememberSaveable { checkValues(supportingText, errorMessage, onBothNull = {}) }

    val waddleColors = WaddleTheme.colors

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    val updatedPrefix: (@Composable () -> Unit)? = remember {
        getUpdatedValue(prefix, prefixText)
    }

    val updatedSuffix: (@Composable () -> Unit)? = remember {
        getUpdatedValue(suffix, suffixText)
    }

    val titleColor = remember(isError, enabled) {
        waddleColors.run {
            when {
                isError -> inputFields.primaryError
                enabled -> inputFields.primaryText
                else -> inputFields.primaryText.copy(alpha = 0.2f)
            }
        }
    }

    var isFocused by remember { mutableStateOf(false) }
    val derivedIsFocused = remember { derivedStateOf { isFocused } }

    val isTyping by remember {
        derivedStateOf {
            textFieldValue.text.isNotEmpty() ||
                    (textFieldValue.text.isNotBlank() && derivedIsFocused.value)
        }
    }

    val textColor by remember(isError, derivedIsFocused, enabled) {
        derivedStateOf {
            when {
                isError -> colors.errorTextColor
                !enabled -> colors.disabledTextColor
                derivedIsFocused.value -> colors.focusedTextColor
                else -> colors.unfocusedTextColor
            }
        }
    }

    val leadingIconColor by remember(isError, derivedIsFocused, enabled) {
        derivedStateOf {
            if (unspecifiedLeadingIconColor) Color.Unspecified
            else when {
                isError -> colors.errorLeadingIconColor
                !enabled -> colors.disabledLeadingIconColor
                derivedIsFocused.value -> colors.focusedLeadingIconColor
                else -> colors.unfocusedLeadingIconColor
            }
        }
    }

    val trailingIconColor by remember(isError, derivedIsFocused, enabled) {
        derivedStateOf {
            if (unspecifiedTrailingIconColor) Color.Unspecified
            else when {
                isError -> colors.errorTrailingIconColor
                !enabled -> colors.disabledTrailingIconColor
                derivedIsFocused.value -> colors.focusedTrailingIconColor
                else -> colors.unfocusedTrailingIconColor
            }
        }
    }

    val updatedLeadingIcon: (@Composable () -> Unit)? =
        getUpdatedValue(leadingIcon, leadingIconRes, "Leading icon", onLeadingIconClicked)


    val updatedTrailingIcon: (@Composable () -> Unit)? =
        getTrailingIcon(trailingIcon, trailingIconRes, isError, onTrailingIconClicked)

    LaunchedEffect(value) {
        if (value != textFieldValue.text) {
            val newCursor = textFieldValue.selection.start.coerceAtMost(value.length)
            textFieldValue = TextFieldValue(text = value, selection = TextRange(newCursor))
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.animateContentSize()
    ) {
        titleText?.let {
            Text(
                text = titleText,
                color = titleColor,
                style = WaddleTheme.typography.body1Medium.Poppins
            )
        }

        BasicTextField(
            modifier = modifier
                .applyIf(isError) {
                    border(
                        width = 1.dp,
                        color = waddleColors.inputFields.primaryError,
                        shape = shape
                    )
                }
                .onFocusChanged {
                    isFocused = it.isFocused || it.isCaptured || it.hasFocus
                },
            value = textFieldValue,
            onValueChange = { newTextFieldValue ->
                textFieldValue = newTextFieldValue
                val text = newTextFieldValue.text

                onValueChange(text)
            },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle.copy(
                color = textColor
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(colors.cursorColor),
            decorationBox = { innerTextField ->
                WaddleTextFieldDecorator(
                    placeholderText,
                    textFieldValue.text,
                    isTyping,
                    derivedIsFocused.value,
                    animatedPlaceholder,
                    innerTextField,
                    updatedLeadingIcon,
                    updatedTrailingIcon,
                    leadingIconColor,
                    trailingIconColor,
                    colors,
                    shape
                )
            }
        )

        if (errorMessage.isNullOrBlank().not()) {
            AnimatedErrorMessage(
                errorState = isError,
                errorText = errorMessage
            )
        }
    }
}

@Preview
@Composable
private fun WaddleTextFieldWrapper2Preview() {
    var text by remember { mutableStateOf("vv vd") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WaddleTextFieldWrapper(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            titleText = "Email",
            placeholderText = "My placeholder",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_credit_card),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_credit_card),
                    contentDescription = null
                )
            },
            animatedPlaceholder = false,
            isError = false
        )

        WaddleTextFieldWrapper(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            titleText = "Email",
            placeholderText = "My placeholder",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_credit_card),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_credit_card),
                    contentDescription = null
                )
            },
            animatedPlaceholder = false,
            isError = true
        )

        WaddleTextFieldWrapper(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            titleText = "Email",
            placeholderText = "My placeholder",
            leadingIconRes = R.drawable.ic_credit_card,
            trailingIconRes = R.drawable.ic_credit_card,
            animatedPlaceholder = true,
            isError = true,
            errorMessage = "Error message"
        )
    }
}