package com.waddleup.core.presentation.components.bottom_sheet.wrapper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.bottom_sheet.model.BottomSheetAction
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.core.presentation.components.button.WaddleSecondaryButton
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme
import kotlinx.coroutines.flow.filter

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WaddleBottomSheetWrapper(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    containerColor: Color,
    shape: Shape,
    onDismiss: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
    positiveBottomSheetAction: BottomSheetAction? = null,
    negativeBottomSheetAction: BottomSheetAction? = null
) {
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.currentValue }
            .filter { it == SheetValue.Hidden }
            .collect { onDismiss?.invoke() }
    }

    ModalBottomSheet(
        modifier = modifier,
        containerColor = containerColor,
        shape = shape,
        sheetState = sheetState,
        onDismissRequest = { onDismiss?.invoke() },
        dragHandle = {},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                content()

                positiveBottomSheetAction?.let { button ->
                    WaddlePrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        buttonText = stringResource(button.label),
                        onClick = { button.action?.invoke() }
                    )
                }

                negativeBottomSheetAction?.let { button ->
                    VerticalSpacer(8.dp)

                    WaddleSecondaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        buttonText = stringResource(button.label),
                        onClick = { button.action?.invoke() }
                    )
                }

                VerticalSpacer(32.dp)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WaddleBottomSheetWrapperPreview() {
    WaddleBottomSheetWrapper(
        sheetState = rememberModalBottomSheetState(),
        containerColor = WaddleTheme.colors.background.primary,
        shape = WaddleTheme.shapes.small,
        onDismiss = {},
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        },
        positiveBottomSheetAction = BottomSheetAction(
            label = R.string.button_next
        ),
        negativeBottomSheetAction = BottomSheetAction(
            label = R.string.button_cancel
        )
    )
}