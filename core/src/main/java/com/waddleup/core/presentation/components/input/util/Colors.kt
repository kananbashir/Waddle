package com.waddleup.core.presentation.components.input.util

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Composable
fun TextFieldDefaults.mainWaddleColors() = WaddleTheme.colors.run {
    val disabledColor = inputFields.primaryText.copy(alpha = 0.2f)
    val disabledErrorColor = inputFields.primaryError.copy(alpha = 0.2f)

    colors(
        focusedTextColor = inputFields.primaryText,
        unfocusedTextColor = inputFields.primaryText.copy(alpha = 0.7f),
        disabledTextColor = disabledColor,
        errorTextColor = inputFields.primaryError,
        focusedContainerColor = inputFields.primaryBackground,
        unfocusedContainerColor = inputFields.primaryBackground,
        disabledContainerColor = inputFields.primaryBackground,
        errorContainerColor = inputFields.primaryBackground,
        cursorColor = buttons.primary,
        errorCursorColor = inputFields.primaryError,
        selectionColors = TextSelectionColors(
            handleColor = buttons.primary,
            backgroundColor = buttons.primary.copy(0.2f)
        ),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = inputFields.primaryText,
        unfocusedLeadingIconColor = inputFields.primaryHint,
        disabledLeadingIconColor = disabledColor,
        errorLeadingIconColor = inputFields.primaryError,
        focusedTrailingIconColor = inputFields.primaryText,
        unfocusedTrailingIconColor = inputFields.primaryHint,
        disabledTrailingIconColor = disabledColor,
        errorTrailingIconColor = inputFields.primaryError,
        focusedLabelColor = inputFields.primaryText,
        unfocusedLabelColor = inputFields.primaryHint,
        disabledLabelColor = disabledColor,
        errorLabelColor = inputFields.primaryError,
        focusedPlaceholderColor = inputFields.primaryHint.copy(0.3f),
        unfocusedPlaceholderColor = inputFields.primaryHint,
        disabledPlaceholderColor = disabledColor,
        errorPlaceholderColor = disabledErrorColor,
        focusedSupportingTextColor = inputFields.primaryText,
        unfocusedSupportingTextColor = inputFields.primaryHint,
        disabledSupportingTextColor = disabledColor,
        errorSupportingTextColor = inputFields.primaryError,
        focusedPrefixColor = inputFields.primaryHint,
        unfocusedPrefixColor = inputFields.primaryHint,
        disabledPrefixColor = disabledColor,
        errorPrefixColor = disabledErrorColor,
        focusedSuffixColor = inputFields.primaryHint,
        unfocusedSuffixColor = inputFields.primaryHint,
        disabledSuffixColor = disabledColor,
        errorSuffixColor = disabledErrorColor
    )
}