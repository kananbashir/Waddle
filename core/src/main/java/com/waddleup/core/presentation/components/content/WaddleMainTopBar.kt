package com.waddleup.core.presentation.components.content

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.waddleup.app.theme.R
import com.waddleup.theme.WaddleTheme
import kotlinx.coroutines.delay

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleMainTopBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onBackClicked: () -> Unit
) {
    var showStartButton by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600)
        showStartButton = true
    }

    TopBarWrapper(
        modifier = modifier,
        textContent = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(title),
                style = WaddleTheme.typography.body1Medium.Poppins,
                textAlign = TextAlign.Center
            )
        },
        leadingContent = {
            AnimatedContent(
                targetState = showStartButton,
            ) {
                if (it) {
                    Icon(
                        modifier = Modifier
                            .clickable(
                                interactionSource = null,
                                indication = null
                            ) { onBackClicked() },
                        painter = painterResource(id = R.drawable.ic_left_arrow),
                        contentDescription = "Navigate back",
                        tint = WaddleTheme.colors.icons.tertiaryTint
                    )
                }
            }
        }
    )
}