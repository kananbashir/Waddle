package com.waddleup.core.base.usecase

import kotlin.coroutines.cancellation.CancellationException

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

abstract class BaseUseCase<in P, R> : UseCase<P, R> {
    protected abstract suspend fun execute(parameters: P): Result<R>

    final override suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            execute(parameters)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}