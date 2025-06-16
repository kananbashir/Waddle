package com.waddleup.settings.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/16/2025
 * @author Kanan Bashir
 */

@Composable
fun SettingsActionHeaderItem(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    isFirstItem: Boolean,
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.background.primary),
    ) {
        if (isFirstItem.not()) {
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.dividers.tertiary
            )
        }

        VerticalSpacer(16.dp)

        Text(
            modifier = Modifier
                .basicMarquee(),
            text = stringResource(text),
            style = types.body2Medium.Poppins,
            color = colors.text.infoHeader,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun SettingsActionHeaderItemPreview() {
    SettingsActionHeaderItem(
        text = R.string.text_account_settings,
        isFirstItem = true
    )
}