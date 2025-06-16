package com.waddleup.settings.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.settings.presentation.model.SettingsAction
import com.waddleup.settings.presentation.model.SettingsActionItem
import com.waddleup.settings.presentation.model.SettingsActionType
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/16/2025
 * @author Kanan Bashir
 */

@Composable
fun SettingsActionItem(
    modifier: Modifier = Modifier,
    settingsAction: SettingsAction,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = settingsAction.item.actionType != SettingsActionType.Switch,
                interactionSource = null,
                indication = null
            ) { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingActionItemLeadingContent(
            settingsActionItem = settingsAction.item
        )

        HorizontalSpacer(16.dp)

        SettingActionItemTrailingContent(
            settingsActionType = settingsAction.item.actionType
        )
    }
}

@Composable
private fun SettingActionItemLeadingContent(
    modifier: Modifier = Modifier,
    settingsActionItem: SettingsActionItem
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .drawBehind {
                    drawCircle(
                        color = colors.icons.quaternaryBackground
                    )
                }
                .padding(10.dp),
            painter = painterResource(settingsActionItem.icon),
            contentDescription = stringResource(settingsActionItem.title),
            tint = colors.icons.quaternaryTint
        )

        HorizontalSpacer(12.dp)

        Text(
            modifier = Modifier
                .basicMarquee(),
            text = stringResource(settingsActionItem.title),
            style = types.body1Medium.Poppins,
            color = colors.text.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun SettingActionItemTrailingContent(
    modifier: Modifier = Modifier,
    settingsActionType: SettingsActionType
) {
    val colors = WaddleTheme.colors

    when (settingsActionType) {
        SettingsActionType.Navigate -> {
            Icon(
                modifier = modifier
                    .padding(end = 8.dp),
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = null,
                tint = colors.icons.quaternaryTint
            )
        }
        SettingsActionType.Expand -> {
            Icon(
                modifier = modifier
                    .padding(end = 8.dp)
                    .rotate(90f),
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = null,
                tint = colors.icons.quaternaryTint
            )
        }
        SettingsActionType.Switch -> {

        }
    }
}

@Preview
@Composable
private fun SettingsActionItemPreview() {
    Box(
        modifier = Modifier
            .padding(32.dp)
            .background(Color.White)
    ) {
        SettingsActionItem(
            settingsAction = SettingsAction.ChangePassword,
            onClick = {}
        )
    }
}