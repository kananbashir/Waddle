package com.waddleup.core.base.usecase

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

interface NoParamUseCase<R> {
    suspend operator fun invoke(): Result<R>
}