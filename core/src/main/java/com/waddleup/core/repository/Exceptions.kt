package com.waddleup.core.repository

import retrofit2.HttpException
import java.io.IOException

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

open class ParsedApiException(
    val httpCode: Int,
    val apiErrorResponse: ApiErrorResponse?,
    cause: HttpException
) : Exception(apiErrorResponse?.message ?: cause.message(), cause) {
    fun getErrorCode(): String? = apiErrorResponse?.errorCode
}

class ValidationApiException(
    httpCode: Int,
    apiErrorResponse: ApiErrorResponse?,
    cause: HttpException
) : ParsedApiException(httpCode, apiErrorResponse, cause)

class NoConnectivityException : IOException("No internet connection available")
class NetworkException(cause: Throwable) : IOException("Network error occurred", cause)
class DatabaseException(cause: Throwable) : Exception("Database error occurred", cause)
class UnauthorizedException(cause: Throwable) : Exception("Unauthorized access", cause)
class ForbiddenException(cause: Throwable) : Exception("Forbidden access", cause)
class NotFoundException(cause: Throwable) : Exception("Resource not found", cause)
class ServerException(cause: Throwable) : Exception("Server error occurred", cause)
class UnknownApiException(cause: Throwable) : Exception("Unknown API error occurred", cause)
class NoDataException : Exception("No data available")