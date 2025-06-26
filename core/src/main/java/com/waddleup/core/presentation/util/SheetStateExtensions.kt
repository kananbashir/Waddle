package com.waddleup.core.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalMaterial3Api::class)
fun SheetState.show(scope: CoroutineScope) = scope.launch { show() }

@OptIn(ExperimentalMaterial3Api::class)
fun SheetState.hide(scope: CoroutineScope) = scope.launch { hide() }