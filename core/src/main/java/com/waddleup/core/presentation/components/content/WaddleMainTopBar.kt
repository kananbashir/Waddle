package com.waddleup.core.presentation.components.content

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    @DrawableRes icon: Int = R.drawable.ic_left_arrow,
    backgroundColor: Color = WaddleTheme.colors.background.primary,
    paddingValues: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
    onBackClicked: () -> Unit
) {
    var showStartButton by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600)
        showStartButton = true
    }

    TopBarWrapper(
        modifier = modifier
            .background(backgroundColor)
            .padding(paddingValues),
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
                transitionSpec = {
                    slideInHorizontally { -it * 2 } + fadeIn() togetherWith
                            slideOutHorizontally { -it * 2 } + fadeOut()
                }
            ) {
                if (it) {
                    LeadingIcon(WaddleTheme.colors.icons.tertiaryTint, icon) { onBackClicked() }
                } else {
                    LeadingIcon(backgroundColor, icon) { onBackClicked() }
                }
            }
        }
    )
}

@Composable
private fun LeadingIcon(
    tint: Color,
    @DrawableRes icon: Int,
    onBackClicked: () -> Unit,
) {
    Icon(
        modifier = Modifier
            .clickable(
                interactionSource = null,
                indication = null
            ) { onBackClicked() },
        painter = painterResource(icon),
        contentDescription = "Navigate back",
        tint = tint
    )
}