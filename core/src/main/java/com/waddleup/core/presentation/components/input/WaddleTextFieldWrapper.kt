package com.waddleup.core.presentation.components.input

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.waddleup.core.R
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
    textStyle: TextStyle = WaddleTheme.typography.body1Medium.Poppins,
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
    onLeadingIconClicked: (() -> Unit)? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    placeholderText: String? = null,
    prefixText: String? = null,
    suffixText: String? = null,
    errorMessage: String? = null,
    titleText: String
) {
    rememberSaveable { checkValues(prefix, prefixText, onBothNull = {}) }
    rememberSaveable { checkValues(suffix, suffixText, onBothNull = {}) }
    rememberSaveable { checkValues(placeholder, placeholderText, onBothNull = {}) }
    rememberSaveable { checkValues(leadingIcon, leadingIconRes, onBothNull = {}) }
    rememberSaveable { checkValues(trailingIcon, trailingIconRes, onBothNull = {}) }
    rememberSaveable { checkValues(supportingText, errorMessage, onBothNull = {}) }

    val waddleColors = WaddleTheme.colors

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }

    val updatedLeadingIcon: (@Composable () -> Unit)? =
        getUpdatedValue(leadingIcon, leadingIconRes, "Leading icon", onLeadingIconClicked)
    val updatedTrailingIcon: (@Composable () -> Unit)? =
        trailingIcon ?: when {
            isError || trailingIconRes != null -> {
                @Composable {
                    var isClicked by remember { mutableStateOf(false) }

                    AnimatedVisibility(
                        visible = isError,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        AnimatedContent(
                            targetState = isClicked,
                            label = ""
                        ) { _ ->
                            Icon(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        isClicked = !isClicked
                                        onTrailingIconClicked?.invoke()
                                    },
                                painter = painterResource(trailingIconRes ?: R.drawable.ic_error),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            else -> null
        }

    val updatedPlaceholder: (@Composable () -> Unit)? = remember {
        getUpdatedValue(placeholder, placeholderText)
    }
    val updatedPrefix: (@Composable () -> Unit)? = remember {
        getUpdatedValue(prefix, prefixText)
    }
    val updatedSuffix: (@Composable () -> Unit)? = remember {
        getUpdatedValue(suffix, suffixText)
    }

    val inputNameColor = remember(isError, enabled) {
        waddleColors.run {
            when {
                isError -> inputFieldError
                enabled -> primaryText
                else -> primaryText.copy(alpha = 0.2f)
            }
        }
    }

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
        Text(
            text = titleText,
            color = inputNameColor,
            style = WaddleTheme.typography.body1Medium.Poppins
        )

        TextField(
            value = textFieldValue,
            onValueChange = { newTextFieldValue ->
                textFieldValue = newTextFieldValue
                val text = newTextFieldValue.text

                onValueChange(text)
            },
            modifier = modifier
                .applyIf(isError) {
                    border(
                        width = 1.dp,
                        color = waddleColors.inputFieldError,
                        shape = shape
                    )
                },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = updatedPlaceholder,
            leadingIcon = updatedLeadingIcon,
            trailingIcon = updatedTrailingIcon,
            prefix = updatedPrefix,
            suffix = updatedSuffix,
            supportingText = supportingText,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )

        AnimatedErrorMessage(
            errorState = isError,
            errorText = errorMessage ?: ""
        )
    }
}

internal fun getUpdatedValue(
    composable: @Composable (() -> Unit)?,
    explicitText: String?
): @Composable (() -> Unit)? {
    return composable ?: explicitText?.let {
        run { @Composable { WaddleTextFieldText(text = it) } }
    }
}

internal fun getUpdatedValue(
    composable: @Composable (() -> Unit)?,
    explicitRes: Int?,
    contentDes: String,
    onClick: (() -> Unit)?
): @Composable (() -> Unit)? {
    return composable ?: explicitRes?.let {
        run {
            @Composable {
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onClick?.invoke()
                        },
                    painter = painterResource(it),
                    contentDescription = contentDes,
                )
            }
        }
    }
}

@Composable
fun TextFieldDefaults.mainWaddleColors() = WaddleTheme.colors.run {
    val disabledColor = inputFieldText.copy(alpha = 0.2f)
    val disabledErrorColor = inputFieldError.copy(alpha = 0.2f)

    colors(
        focusedTextColor = inputFieldText,
        unfocusedTextColor = inputFieldText.copy(alpha = 0.7f),
        disabledTextColor = disabledColor,
        errorTextColor = inputFieldError,
        focusedContainerColor = inputFieldBackground,
        unfocusedContainerColor = inputFieldBackground,
        disabledContainerColor = inputFieldBackground,
        errorContainerColor = inputFieldBackground,
        cursorColor = primaryButton,
        errorCursorColor = inputFieldError,
        selectionColors = TextSelectionColors(
            handleColor = primaryButton,
            backgroundColor = primaryButton.copy(0.2f)
        ),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = inputFieldText,
        unfocusedLeadingIconColor = inputFieldHint,
        disabledLeadingIconColor = disabledColor,
        errorLeadingIconColor = inputFieldError,
        focusedTrailingIconColor = inputFieldText,
        unfocusedTrailingIconColor = inputFieldHint,
        disabledTrailingIconColor = disabledColor,
        errorTrailingIconColor = inputFieldError,
        focusedLabelColor = inputFieldText,
        unfocusedLabelColor = inputFieldHint,
        disabledLabelColor = disabledColor,
        errorLabelColor = inputFieldError,
        focusedPlaceholderColor = inputFieldHint.copy(0.3f),
        unfocusedPlaceholderColor = inputFieldHint,
        disabledPlaceholderColor = disabledColor,
        errorPlaceholderColor = disabledErrorColor,
        focusedSupportingTextColor = inputFieldText,
        unfocusedSupportingTextColor = inputFieldHint,
        disabledSupportingTextColor = disabledColor,
        errorSupportingTextColor = inputFieldError,
        focusedPrefixColor = inputFieldHint,
        unfocusedPrefixColor = inputFieldHint,
        disabledPrefixColor = disabledColor,
        errorPrefixColor = disabledErrorColor,
        focusedSuffixColor = inputFieldHint,
        unfocusedSuffixColor = inputFieldHint,
        disabledSuffixColor = disabledColor,
        errorSuffixColor = disabledErrorColor
    )
}