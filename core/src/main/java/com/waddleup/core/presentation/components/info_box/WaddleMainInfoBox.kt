package com.waddleup.core.presentation.components.info_box

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleMainInfoBox(
    modifier: Modifier = Modifier,
    @StringRes headerText: Int,
    @StringRes bodyText: Int,
    @StringRes buttonText: Int,
    @DrawableRes trailingIcon: Int,
    onButtonClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    WaddleMainInfoBoxWrapper(
        modifier = modifier,
        headerText = stringResource(headerText),
        bodyText = stringResource(bodyText),
        buttonText = stringResource(buttonText),
        trailingIcon = trailingIcon,
        onButtonClicked = onButtonClicked,
        onCloseClicked = onCloseClicked
    )
}


@Composable
fun WaddleMainInfoBox(
    modifier: Modifier = Modifier,
    @StringRes headerText: Int,
    @StringRes bodyText: Int,
    @DrawableRes trailingIcon: Int,
    onCloseClicked: () -> Unit
) {
    WaddleMainInfoBoxWrapper(
        modifier = modifier,
        headerText = stringResource(headerText),
        bodyText = stringResource(bodyText),
        buttonText = null,
        onButtonClicked = null,
        trailingIcon = trailingIcon,
        onCloseClicked = onCloseClicked
    )
}

@Composable
private fun WaddleMainInfoBoxWrapper(
    modifier: Modifier = Modifier,
    headerText: String,
    bodyText: String,
    buttonText: String? = null,
    @DrawableRes trailingIcon: Int,
    onButtonClicked: (() -> Unit)? = null,
    onCloseClicked: () -> Unit
) {
    val colors = WaddleTheme.colors
    val shapes = WaddleTheme.shapes
    val types = WaddleTheme.typography

    WaddleInfoBoxWrapper(
        modifier = modifier,
        headerContent = {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp, top = 16.dp),
                text = headerText,
                style = types.overlineMedium.Poppins,
                color = colors.text.tertiary
            )
        },
        bodyContent = {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(0.8f),
                text = bodyText,
                style = types.body1Bold.Poppins,
                color = colors.text.tertiary
            )
        },
        bottomActionContent = buttonText?.run {
            {
                WaddlePrimaryButton(
                    modifier = Modifier
                        .padding(start = 20.dp, bottom = 16.dp)
                        .fillMaxWidth(0.65f),
                    colors = colors.containers.run {
                        ButtonDefaults.buttonColors(
                            containerColor = primaryBackground,
                            contentColor = primaryText,
                            disabledContainerColor = primaryBackground.copy(alpha = 0.2f),
                            disabledContentColor = primaryText.copy(alpha = 0.2f)
                        )
                    },
                    shape = shapes.extraSmall,
                    content = {
                        Text(
                            text = buttonText,
                            style = types.body2SemiBold.Poppins,
                            color = colors.containers.primaryText
                        )
                    },
                    onClick = { onButtonClicked?.invoke() }
                )
            }
        },
        trailingIconContent = {
            Icon(
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .drawBehind {
                        drawCircle(
                            color = colors.icons.secondaryTint.copy(alpha = 0.1f)
                        )
                    }
                    .padding(2.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) { onCloseClicked() },
                painter = painterResource(trailingIcon),
                contentDescription = "Close info box",
                tint = colors.icons.secondaryTint
            )
        }
    )
}