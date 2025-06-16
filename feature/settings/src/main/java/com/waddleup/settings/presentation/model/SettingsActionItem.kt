package com.waddleup.settings.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

/**
 * Created on 6/15/2025
 * @author Kanan Bashir
 */

@Immutable
data class SettingsActionItem(
    val id: Int,
    @DrawableRes val icon: Int,
    @StringRes val iconContentDescription: Int? = null,
    @StringRes val title: Int,
    val actionType: SettingsActionType,
)
