package com.waddleup.core.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.waddleup.core.presentation.components.bottom_sheet.model.BottomSheetAction
import com.waddleup.core.presentation.components.bottom_sheet.wrapper.WaddleBottomSheetWrapper
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaddleMainBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    containerColor: Color = WaddleTheme.colors.background.primary,
    shape: Shape = WaddleTheme.shapes.small,
    onDismiss: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
    positiveBottomSheetAction: BottomSheetAction? = null,
    negativeBottomSheetAction: BottomSheetAction? = null
) {
    WaddleBottomSheetWrapper(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = containerColor,
        shape = shape,
        content = content,
        onDismiss = onDismiss,
        positiveBottomSheetAction = positiveBottomSheetAction,
        negativeBottomSheetAction = negativeBottomSheetAction
    )
}