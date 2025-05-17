package com.waddleup.onboarding.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.waddleup.onboarding.R

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Immutable
@Keep
enum class OnboardingModel(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val subTitle: Int
) {
    FirstPage(
        image = R.drawable.mascot_onboarding_1,
        title = R.string.ui_text_title_onboarding_1,
        subTitle = R.string.ui_text_sub_title_onboarding_1
    ),
    SecondPage(
        image = R.drawable.mascot_onboarding_1,
        title = R.string.ui_text_title_onboarding_2,
        subTitle = R.string.ui_text_sub_title_onboarding_2
    ),
    ThirdPage(
        image = R.drawable.mascot_onboarding_1,
        title = R.string.ui_text_title_onboarding_3,
        subTitle = R.string.ui_text_sub_title_onboarding_3
    );
}
