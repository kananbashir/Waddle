package com.waddleup.auth.password_recovery.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoveryFirstPage
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoveryFourthPage
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoverySecondPage
import com.waddleup.auth.password_recovery.content.pager.PasswordRecoveryThirdPage
import com.waddleup.auth.viewmodel.AuthIntent
import com.waddleup.auth.viewmodel.AuthState
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.core.presentation.components.button.WaddleSecondaryButton
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.core.presentation.components.content.WaddleMainTopBar
import kotlin.math.min

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
fun PasswordRecoveryContent(
    state: AuthState.PasswordRecoveryState,
    onIntent: (AuthIntent.PasswordRecovery) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 4 }
    )

    LaunchedEffect(key1 = state.currentPage) {
        pagerState.animateScrollToPage(state.currentPage)
    }

    WaddleMainContentWrapper(
        paddingValues = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        isLoading = state.isLoading,
        topBar = {
            if (state.currentPage < 2) {
                WaddleMainTopBar(
                    title = R.string.top_bar_password_recovery,
                    onBackClicked = { onIntent(AuthIntent.PasswordRecovery.AbortProcess) }
                )
            }
        },

        content = {
            HorizontalPager(
                userScrollEnabled = false,
                state = pagerState,
                pageSpacing = 16.dp,
                verticalAlignment = Alignment.Top
            ) {page ->
                when (page) {
                    0 -> PasswordRecoveryFirstPage(state, onIntent)
                    1 -> PasswordRecoverySecondPage(state, onIntent) {}
                    2 -> PasswordRecoveryThirdPage(state, onIntent) {}
                    3 -> PasswordRecoveryFourthPage()
                }
            }
        },

        bottomBar = {
            val effectivePage = (pagerState.currentPage + pagerState.currentPageOffsetFraction)
                .coerceIn(0f, (pagerState.pageCount-1).toFloat())
            val cancelButtonWeight = when {
                pagerState.targetPage != pagerState.pageCount-1 && effectivePage <= pagerState.pageCount-2 -> min(effectivePage, 1f)
                effectivePage == (pagerState.pageCount-1).toFloat() -> effectivePage % 1.0f
                else -> 1 - (effectivePage % 1.0f)
            }

            Row {
                if (cancelButtonWeight > 0f) {
                    Box(
                        modifier = Modifier.weight(cancelButtonWeight.coerceAtMost(1f))
                    ) {
                        WaddleSecondaryButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            buttonText = stringResource(id = R.string.button_cancel),
                            onClick = { onIntent(AuthIntent.PasswordRecovery.AbortProcess) }
                        )
                    }
                }

                WaddlePrimaryButton(
                    modifier = Modifier
                        .weight(1f),
                    buttonText = stringResource(
                        id = if (pagerState.currentPage < 3) R.string.button_next
                        else R.string.button_go_to_login),
                    isEnabled = state.run {
                        when (pagerState.currentPage) {
                            0 -> isEmailValid && !isLoading
                            1 -> isConfirmationCodeValid && !isLoading
                            2 -> arePasswordsValid && !isLoading
                            3 -> true
                            else -> false
                        }
                    },
                    onClick = {
                        onIntent(
                            when (pagerState.currentPage) {
                                0 -> AuthIntent.PasswordRecovery.SubmitEmailConfirmation
                                1 -> AuthIntent.PasswordRecovery.SubmitConfirmationCode
                                2 -> AuthIntent.PasswordRecovery.SubmitCreateNewPassword
                                3 -> AuthIntent.PasswordRecovery.SubmitGoToLogin
                                else -> return@WaddlePrimaryButton
                            }
                        )
                    }
                )
            }
        },

        onBack = { onIntent(AuthIntent.PasswordRecovery.AbortProcess) }
    )
}