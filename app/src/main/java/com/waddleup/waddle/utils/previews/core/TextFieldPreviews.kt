package com.waddleup.waddle.utils.previews.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.waddleup.theme.color.LocalAppColors

/**
 * Created on 3/26/2025
 * @author Kanan Bashir
 */

@Preview
@Composable
private fun WaddleTextFieldEnabled(
    @PreviewParameter(TextFieldConfigProvider::class) config: WaddleTextFieldConfig
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(LocalAppColors.current.background.primary)
            .padding(16.dp)
    ) {
//        WaddleTextField(
//            modifier = Modifier.fillMaxWidth(),
//            validatorTag = "text_field",
//            value = text,
//            enabled = config.isEnabled,
//            isError = config.isError,
//            onValueChange = { v ->
//                text = v
//            },
//            titleText = "Email",
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Email,
//                    contentDescription = null
//                )
//            },
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Email,
//                    contentDescription = null
//                )
//            },
//            placeholder = {
//                Text(text = "Placeholder")
//            },
//            prefix = {
//                Text(text = "Prefix:")
//            },
//            suffix = {
//                Text(text = "Suffix")
//            },
//        )
    }
}

private class TextFieldConfigProvider: PreviewParameterProvider<WaddleTextFieldConfig> {
    override val values: Sequence<WaddleTextFieldConfig>
        get() = sequenceOf(
            WaddleTextFieldConfig(
                isEnabled = false,
                isError = false
            ),
            WaddleTextFieldConfig(
                isEnabled = true,
                isError = false
            ),
            WaddleTextFieldConfig(
                isEnabled = true,
                isError = true
            ),
        )
}

private data class WaddleTextFieldConfig(
    val isEnabled: Boolean,
    val isError: Boolean
)