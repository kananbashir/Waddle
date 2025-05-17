package com.waddleup.core.base.usecase

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

interface UseCase<in P, R> {
    suspend operator fun invoke(parameters: P): Result<R>
}