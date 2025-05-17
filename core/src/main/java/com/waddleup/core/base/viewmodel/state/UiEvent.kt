package com.waddleup.core.base.viewmodel.state

/**
 * Created on 5/14/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
sealed class UiEvent {
    data class ShowToast(
        val message: String,
        val duration: ToastDuration = ToastDuration.SHORT
    ) : UiEvent()

    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null,
        val onAction: (() -> Unit)? = null
    ) : UiEvent()

    data class ShowError(val message: String) : UiEvent()

    data class Navigate(
        val route: Any,
        val popUpTo: Any? = null,
        val inclusive: Boolean = false
    ) : UiEvent()

    data object NavigateBack : UiEvent()

    data class ShowDialog(
        val title: String,
        val message: String,
        val positiveAction: DialogAction? = null,
        val negativeAction: DialogAction? = null
    ) : UiEvent()
}

data class DialogAction(
    val label: String,
    val action: () -> Unit
)

@Suppress("unused")
enum class ToastDuration {
    SHORT, LONG
}