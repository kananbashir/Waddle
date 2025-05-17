package com.waddleup.core.base.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

abstract class BaseFlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {
    protected abstract fun execute(parameters: P): Flow<R>

    operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters)
            .flowOn(dispatcher)
            .catch { e ->
                if (e is CancellationException) {
                    throw e
                }
                Timber.e(e, "Error in FlowUseCase")
                throw e
            }
    }
}