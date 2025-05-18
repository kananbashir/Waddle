package com.waddleup.core.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.core.base.viewmodel.state.UiEvent
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
inline fun <S, I, reified T: BaseViewModel<S, I>>BaseScreen(
    crossinline onUiEvent: (UiEvent) -> Unit,
    viewModelStoreOwner: ViewModelStoreOwner? = LocalViewModelStoreOwner.current,
    viewModel: T = koinViewModel(viewModelStoreOwner = viewModelStoreOwner
        ?: error("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner")),
    content: @Composable (
        viewModel: T,
        uiState: State<S>
    ) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState = viewModel.uiState
        .filterNotNull()
        .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        .collectAsStateWithLifecycle(
            initialValue = viewModel.initialState
        )

    LifecycleStartEffect(viewModel) {
        val job = viewModel.uiEvent
            .onEach { onUiEvent(it) }
            .launchIn(lifecycle.coroutineScope)

        onStopOrDispose { job.cancel() }
    }

    content(viewModel, uiState)
}

