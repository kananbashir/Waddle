package com.waddleup.core.presentation.components.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.input.util.secondaryWaddleColors
import com.waddleup.core.presentation.components.input.wrapper.WaddleTextFieldWrapper
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleSecondaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = WaddleTheme.typography.body1Medium.Poppins,
    onValueChange: (value: String) -> Unit,
    placeholderText: String? = null,
    prefixText: String? = null,
    suffixText: String? = null,
    errorMessage: String? = null,
    titleText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    @DrawableRes leadingIconRes: Int? = null,
    @DrawableRes trailingIconRes: Int? = null,
    unspecifiedLeadingIconColor: Boolean = false,
    unspecifiedTrailingIconColor: Boolean = false,
    onLeadingIconClicked: (() -> Unit)? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    colors: TextFieldColors = TextFieldDefaults.secondaryWaddleColors()
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
        unspecifiedLeadingIconColor = unspecifiedLeadingIconColor,
        unspecifiedTrailingIconColor = unspecifiedTrailingIconColor,
        onLeadingIconClicked = onLeadingIconClicked,
        onTrailingIconClicked = onTrailingIconClicked,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors,
        animatedPlaceholder = true
    )
}

@Preview
@Composable
private fun WaddleSecondaryTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(32.dp)
    ) {
        WaddleSecondaryTextField(
            value = text,
            onValueChange = { text = it },
            titleText = "Email",
            placeholderText = "Email",
            leadingIconRes = R.drawable.ic_down_arrow,
            trailingIconRes = R.drawable.ic_down_arrow
        )
    }
}