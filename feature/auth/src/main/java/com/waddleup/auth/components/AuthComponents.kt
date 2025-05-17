package com.waddleup.auth.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.text.WaddleAutoResizedText
import com.waddleup.core.presentation.components.text.WaddleClickableText
import com.waddleup.core.presentation.components.text.util.ClickableTextParams
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

typealias core_R_drawable = com.waddleup.core.R.drawable
typealias auth_R_drawable = com.waddleup.auth.R.drawable

@Composable
internal fun AuthHeader(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = WaddleTheme.colors.primaryText,
            style = WaddleTheme.typography.headline2Medium.Poppins,
            textAlign = TextAlign.Center
        )

        Text(
            text = subtitle,
            color = WaddleTheme.colors.primaryText,
            style = WaddleTheme.typography.body2Medium.Poppins,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = WaddleTheme.colors.primaryDivider
        )
        Text(
            text = "Or",
            color = WaddleTheme.colors.primaryText,
            style = WaddleTheme.typography.body2Regular.Poppins
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = WaddleTheme.colors.primaryDivider
        )
    }
}

@Composable
internal fun LoginWithButtons() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(WaddleTheme.shapes.small)
                .background(WaddleTheme.colors.primaryContainerBackground),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = auth_R_drawable.ic_facebook),
//                    imageVector = Icons.Default.CheckCircle,
                contentDescription = "Login with facebook"
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(WaddleTheme.shapes.small)
                .background(WaddleTheme.colors.primaryContainerBackground),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = auth_R_drawable.ic_google),
//                    imageVector = Icons.Default.CheckCircle,
                contentDescription = "Login with google"
            )
        }
    }
}

@Composable
internal fun FooterAccountLoginOrRegisterText(
    modifier: Modifier = Modifier,
    @StringRes fullText: Int,
    @StringRes clickableText: Int,
    clickableTextTag: String,
    onLinkClicked: () -> Unit
) {
    WaddleClickableText(
        fullText = stringResource(id = fullText),
        links = listOf(
            ClickableTextParams(
                tag = clickableTextTag,
                text = stringResource(id = clickableText),
                color = WaddleTheme.colors.primaryText,
                style = WaddleTheme.typography.headline1Bold.Poppins,
                linkInteractionListener = {
                    onLinkClicked()
                }
            )
        ),
        textContent = { annotatedText ->
            WaddleAutoResizedText(
                modifier = modifier
                    .fillMaxWidth(),
                text = annotatedText,
                style = WaddleTheme.typography.headline1Bold.Poppins,
                color = WaddleTheme.colors.primaryText,
                textAlign = TextAlign.Center,
            )
        }
    )
}