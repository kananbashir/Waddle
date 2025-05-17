package com.waddleup.core.base.usecase

import kotlin.coroutines.cancellation.CancellationException

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

abstract class BaseNoParamUseCase<R> : NoParamUseCase<R> {
    protected abstract suspend fun execute(): Result<R>

    final override suspend operator fun invoke(): Result<R> {
        return try {
            val result = execute()
            Result.Success(result.getOrNull()!!)
        } catch (e: CancellationException) {
            // Rethrow cancellation exceptions to properly handle coroutine cancellation
            throw e
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}