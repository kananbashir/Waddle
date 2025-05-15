package com.waddleup.waddle.utils.previews.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.waddleup.onboarding.presentation.components.OnboardingScreen
import com.waddleup.onboarding.presentation.model.OnboardingModel
import com.waddleup.theme.LocalAppColors
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

//@PreviewScreenSizes
//@PreviewFontScale
@Preview
@Composable
private fun OnboardingPreview() {
    WaddleTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalAppColors.current.primaryBackground)
        ) {
            OnboardingScreen(
                pages = listOf(
                    OnboardingModel.FirstPage,
                    OnboardingModel.SecondPage,
                    OnboardingModel.ThirdPage
                ),
                onOnboardingCompleted = {}
            )
        }
    }
}