package com.waddleup.waddle.utils.previews.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.checkbox.WaddleCheckBox

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Preview
@Composable
private fun WaddleCheckBoxPreview() {
    var isChecked1 by remember { mutableStateOf(false) }
    var isChecked2 by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            WaddleCheckBox(
                isChecked = isChecked1,
                isEnabled = true,
                onValueChange = { isChecked1 = !isChecked1 }
            )

            WaddleCheckBox(
                isChecked = isChecked2,
                isEnabled = false,
                onValueChange = { isChecked2 = !isChecked2 }
            )
        }
    }
}