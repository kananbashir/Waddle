package com.waddleup.core.presentation.components.bottom_sheet.model

import androidx.annotation.StringRes

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

data class BottomSheetAction(
    @StringRes val label: Int,
    val action: (() -> Unit)? = null
)