package com.waddleup.settings.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.info_box.WaddleMainInfoBox

/**
 * Created on 6/15/2025
 * @author Kanan Bashir
 */

@Composable
fun SettingsInfoBox(
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit
) {
    WaddleMainInfoBox(
        modifier = modifier,
        headerText = R.string.settings_info_box_header,
        bodyText = R.string.settings_info_box_body,
        trailingIcon = R.drawable.ic_close,
        onTrailingIconClicked = onCloseClicked
    )
}