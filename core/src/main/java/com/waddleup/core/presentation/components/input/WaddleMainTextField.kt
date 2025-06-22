package com.waddleup.core.presentation.components.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.waddleup.core.presentation.components.input.wrapper.WaddleTextFieldWrapper
import com.waddleup.core.presentation.components.input.util.mainWaddleColors
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleMainTextField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = WaddleTheme.typography.body1Medium.Poppins,
    onValueChange: (value: String) -> Unit,
    placeholderText: String? = null,
    prefixText: String? = null,
    suffixText: String? = null,
    errorMessage: String? = null,
    titleText: String,
    enabled: Boolean = true,
    isError: Boolean = false,
    @DrawableRes leadingIconRes: Int? = null,
    @DrawableRes trailingIconRes: Int? = null,
    onLeadingIconClicked: (() -> Unit)? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    colors: TextFieldColors = TextFieldDefaults.mainWaddleColors()
) {
    WaddleTextFieldWrapper(
        modifier = modifier,
        value = value,
        textStyle = textStyle,
        onValueChange = onValueChange,
        placeholderText = placeholderText,
        prefixText = prefixText,
        suffixText = suffixText,
        errorMessage = errorMessage,
        titleText = titleText,
        enabled = enabled,
        isError = isError,
        leadingIconRes = leadingIconRes,
        trailingIconRes = trailingIconRes,
        onLeadingIconClicked = onLeadingIconClicked,
        onTrailingIconClicked = onTrailingIconClicked,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors,
        animatedPlaceholder = false
    )
}