package com.waddleup.waddle.utils.previews.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.waddleup.home.presentation.content.HomeContent
import com.waddleup.home.viewmodel.state.HomeState

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    device = "spec:width = 411dp, height = 891dp, orientation = portrait, dpi = 420",
)
@Composable
private fun HomePreview() {
    HomeContent(
        state = HomeState(
            dailyLimit = 25.4,
            totalSaveOfMonth = 12.55,
            totalSpendingOfTheMonth = 1247.5
        ),
        onIntent = {},
        onEvent = {}
    )
}