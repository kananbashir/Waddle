package com.waddleup.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waddleup.core.base.usecase.NoParamUseCase
import com.waddleup.core.base.usecase.UseCase
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.repository.DatabaseException
import com.waddleup.core.repository.ForbiddenException
import com.waddleup.core.repository.NoConnectivityException
import com.waddleup.core.repository.ServerException
import com.waddleup.core.repository.UnauthorizedException
import com.waddleup.core.repository.NetworkException
import com.waddleup.core.repository.NotFoundException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import com.waddleup.core.base.usecase.Result
import timber.log.Timber

/**
 * Created on 5/14/2025
 * @author Kanan Bashir
 */

abstract class BaseViewModel<State, Intent>(
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<State?>(initialState)
    val uiState: StateFlow<State?> = _uiState.asStateFlow()

    abstract val initialState: State
    open fun onIntent(intent: Intent) { /* only override when needed */ }

    val currentUiStateData: State?
        get() = _uiState.value

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.tag("${this::class.simpleName}_ex_handler").e("Caught exception in ViewModel: ${throwable.message}")
        throwable.printStackTrace()
        handleCoroutineException(throwable)
    }

    protected val safeCoroutineScope = viewModelScope + coroutineExceptionHandler

    fun safeLaunch(
        dispatcher: CoroutineDispatcher = dispatchersProvider.io,
        onStart: (() -> Unit)? = null,
        onCompleted: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        val context = dispatcher + coroutineExceptionHandler
        return viewModelScope.launch(context) {
            try {
                onStart?.invoke()
                block()
            } catch (e: Exception) {
                onError?.invoke(e) ?: handleCoroutineException(e)
            } finally {
                onCompleted?.invoke()
            }
        }
    }

    fun <T> Flow<T>.safeLaunch(
        onStart: () -> Unit = {},
        onCompleted: suspend () -> Unit = {},
        onError: ((Throwable) -> Unit)? = null,
        onCollected: suspend (T) -> Unit
    ): Job {
        return this
            .flowOn(dispatchersProvider.io)
            .onEach {
                onStart()
                onCollected(it)
            }
            .catch { e ->
                onError?.invoke(e) ?: handleCoroutineException(e)
            }
            .onCompletion { onCompleted() }
            .flowOn(dispatchersProvider.main)
            .launchIn(viewModelScope)
    }

    protected open fun handleCoroutineException(throwable: Throwable) {
        throwable.printStackTrace()
        sendEvent(UiEvent.ShowError(throwable.message ?: "An error occurred"))
    }

    protected fun setState(producer: (State?) -> State?) {
        _uiState.update { producer(it) }
    }

    fun sendEvent(event: UiEvent) = launchMain { _uiEvent.send(event) }

    protected fun launchMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatchersProvider.main) {
            block()
        }
    }

    protected fun launchIo(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatchersProvider.io) {
            block()
        }
    }

    protected suspend fun <T> withMainContext(block: suspend CoroutineScope.() -> T): T {
        return withContext(dispatchersProvider.main) {
            block()
        }
    }

    protected suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T {
        return withContext(dispatchersProvider.io) {
            block()
        }
    }

    open fun parseErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is NoConnectivityException -> "No internet connection. Please check your network settings."
            is NetworkException -> "Network error. Please check your connection."
            is UnauthorizedException -> "Authentication error. Please log in again."
            is ForbiddenException -> "You don't have permission to access this resource."
            is NotFoundException -> "Resource not found."
            is ServerException -> "Server error. Please try again later."
            is DatabaseException -> "A database error occurred. Please restart the app."
            is java.net.UnknownHostException -> "Network error. Please check your connection."
            is java.net.SocketTimeoutException -> "Request timed out. Please try again."
            is retrofit2.HttpException -> {
                when (throwable.code()) {
                    401 -> "Authentication error. Please log in again."
                    403 -> "You don't have permission to access this resource."
                    404 -> "Resource not found."
                    500 -> "Server error. Please try again later."
                    else -> "Error code: ${throwable.code()}"
                }
            }
            else -> throwable.message ?: "An unknown error occurred"
        }
    }

    fun <P, R>execute(
        useCase: UseCase<P, R>,
        params: P,
        onStart: (() -> Unit)? = null,
        onSuccess: State.(result: R) -> Unit?,
        onError: (parsedErrorMessage: String, throwable: Throwable?) -> Unit,
        onCompleted: (() -> Unit)? = null
    ): Job {
        return safeLaunch(
            onStart = onStart,
            onError = {
                onError(it.message ?: "An error occurred", it)
            },
            onCompleted = onCompleted
        ) {
            handleUseCaseResult(useCase(params), onSuccess)
        }
    }

    fun <R>execute(
        useCase: NoParamUseCase<R>,
        onStart: (() -> Unit)? = null,
        onSuccess: State.(result: R) -> Unit?,
        onError: (parsedErrorMessage: String, throwable: Throwable?) -> Unit,
        onCompleted: (() -> Unit)? = null
    ): Job {
        return safeLaunch(
            onStart = onStart,
            onError = {
                onError(it.message ?: "An error occurred", it)
            },
            onCompleted = onCompleted
        ) {
            handleUseCaseResult(useCase(), onSuccess)
        }
    }

    private fun <R> handleUseCaseResult(
        result: Result<R>,
        onSuccess: State.(result: R) -> Unit?
    ) {
        val lastData = _uiState.value ?: initialState
        when (result) {
            is Result.Success -> lastData.onSuccess(result.data)
            is Result.Error -> throw result.exception
        }
    }
}