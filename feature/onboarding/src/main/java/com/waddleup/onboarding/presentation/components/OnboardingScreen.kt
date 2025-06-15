package com.waddleup.onboarding.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.core.presentation.components.button.WaddleSecondaryButton
import com.waddleup.app.theme.R
import com.waddleup.onboarding.presentation.model.OnboardingModel
import com.waddleup.theme.WaddleTheme
import kotlinx.coroutines.launch
import kotlin.math.min

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    pages: List<OnboardingModel>,
    onOnboardingCompleted: () -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WaddleTheme.colors.background.primary)
            .padding(bottom = 16.dp)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HorizontalPagerIndicator(
            pagesSize = pages.size,
            indicatorPassiveColor = WaddleTheme.colors.indicators.secondary,
            indicatorActiveColor = WaddleTheme.colors.indicators.primary,
            pagerState = pagerState
        )

        HorizontalPager(
            modifier = Modifier
                .padding(top = 24.dp)
                .weight(1f),
            state = pagerState
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(id = pages[page].title),
                        color = WaddleTheme.colors.text.primary,
                        style = WaddleTheme.typography.headline2Bold.PlusJakarta
                    )

                    Text(
                        text = stringResource(id = pages[page].subTitle),
                        color = WaddleTheme.colors.text.primary,
                        style = WaddleTheme.typography.body2Medium.PlusJakarta
                    )
                }

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = pages[page].image),
                    contentDescription = "Step ${page + 1} onboarding image"
                )
            }
        }

        val effectivePage = (pagerState.currentPage + pagerState.currentPageOffsetFraction)
            .coerceIn(0f, (pages.size-1).toFloat())
        val backButtonWeight = when {
            pagerState.targetPage != pages.size-1 && effectivePage <= pages.size-2 -> min(effectivePage, 1f)
            effectivePage == (pages.size-1).toFloat() -> effectivePage % 1.0f
            else -> 1 - (effectivePage % 1.0f)
        }

        val nextButton = if (pagerState.currentPage != pages.size-1) stringResource(id = R.string.button_next)
        else stringResource(id = R.string.button_start)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (backButtonWeight > 0f) {
                Box(
                    modifier = Modifier.weight(backButtonWeight.coerceAtMost(1f))
                ) {
                    WaddleSecondaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        buttonText = stringResource(id = R.string.button_previous),
                        onClick = {
                            if (pagerState.currentPage > 0) {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage - 1,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    )
                                }
                            }
                        }
                    )
                }
            }

            WaddlePrimaryButton(
                modifier = Modifier
                    .weight(1f),
                buttonText = nextButton,
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                    stiffness = Spring.StiffnessVeryLow
                                )
                            )
                        }
                    } else {
                        onOnboardingCompleted()
                    }
                }
            )
        }
    }
}

@Composable
private fun HorizontalPagerIndicator(
    pagesSize: Int,
    indicatorPassiveColor: Color,
    indicatorActiveColor: Color,
    pagerState: PagerState
) {
    val animatedProgress by animateFloatAsState(
        targetValue = (pagerState.currentPage + 1 + pagerState.currentPageOffsetFraction) / (pagesSize - 1),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessVeryLow
        ),
        label = "Indicator Progress"
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        val paddingBetween = 2.dp.toPx()
        val indicatorWidth = size.width / pagesSize

        drawLine(
            color = indicatorPassiveColor,
            start = Offset.Zero,
            end = Offset(x = size.width, y = 0f),
            strokeWidth = 6f,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(indicatorWidth-paddingBetween, paddingBetween),
            )
        )

        drawLine(
            color = indicatorActiveColor,
            start = Offset.Zero,
            end = Offset(x = indicatorWidth * (pagesSize-1) * animatedProgress, y = 0f),
            strokeWidth = 6f,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(indicatorWidth-paddingBetween, paddingBetween)
            )
        )
    }
}