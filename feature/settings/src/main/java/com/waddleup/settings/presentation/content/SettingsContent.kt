package com.waddleup.settings.presentation.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.settings.presentation.component.SettingsActionHeaderItem
import com.waddleup.settings.presentation.component.SettingsActionItem
import com.waddleup.settings.presentation.component.SettingsInfoBox
import com.waddleup.settings.presentation.component.SettingsTopBar
import com.waddleup.settings.presentation.model.SettingsActionHeader
import com.waddleup.settings.viewmodel.state.SettingsIntent
import com.waddleup.settings.viewmodel.state.SettingsState
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsContent(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    val colors = WaddleTheme.colors
    val density = LocalDensity.current
    var infoBoxHeight by remember { mutableIntStateOf(0) }
    var animatedInfoBoxHeight = animateIntAsState(
        targetValue = if (state.showInfoBox) infoBoxHeight else 0,
        animationSpec = if (state.showInfoBox) getInfoBoxInAnimationSpec<Int>()
        else getInfoBoxOutAnimationSpec<Int>(),
        label = ""
    )


    WaddleMainContentWrapper(
        paddingValues = PaddingValues(),
        includeBottomNavPadding = true,

        topBar = {
            SettingsTopBar(
                onBackClicked = { onEvent(UiEvent.NavigateBack) }
            )
        },

        content = {
            Box(
                contentAlignment = Alignment.TopStart
            ) {
                AnimatedVisibility(
                    modifier = Modifier
                        .onSizeChanged {
                            if (infoBoxHeight == 0) infoBoxHeight = it.height
                        },
                    visible = state.showInfoBox,
                    enter = slideInVertically(animationSpec = getInfoBoxInAnimationSpec<IntOffset>()) { -it/2 } + fadeIn(),
                    exit = slideOutVertically(animationSpec = getInfoBoxOutAnimationSpec<IntOffset>()) { -it/2 } + fadeOut()
                ) {
                    SettingsInfoBox(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = (16.5).dp),
                        onCloseClicked = { onIntent(SettingsIntent.InfoBoxCloseClicked) }
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .offset {
                            IntOffset(x = 0, y = animatedInfoBoxHeight.value)
                        }
                        .background(colors.background.primary),
                ) {
                    SettingsActionHeader.entries.forEachIndexed { index, header ->
                        stickyHeader {
                            SettingsActionHeaderItem(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                text = header.title,
                                isFirstItem = index == 0
                            )
                        }

                        items(header.actions) {
                            key(it.item.id) {
                                SettingsActionItem(
                                    settingsAction = it,
                                    onClick = {}
                                )
                            }
                        }
                    }

                    item {
                        if (animatedInfoBoxHeight.value != 0) {
                            Spacer(modifier = Modifier.height(with(density) { animatedInfoBoxHeight.value.toDp() }))
                        }
                    }
                }
            }
        }
    )
}

private fun <T>getInfoBoxInAnimationSpec() =
    tween<T>(durationMillis = 600, delayMillis = 200, easing = FastOutSlowInEasing)

private fun <T>getInfoBoxOutAnimationSpec() =
    tween<T>(easing = FastOutSlowInEasing)