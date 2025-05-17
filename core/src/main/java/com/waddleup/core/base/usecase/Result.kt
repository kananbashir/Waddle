package com.waddleup.core.base.usecase

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val exception: Throwable,
        val message: String = exception.localizedMessage ?: "An unexpected error occurred"
    ) : Result<Nothing>()

    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> null
    }

    fun isSuccess(): Boolean = this is Success
}