package com.waddleup.core.repository

import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.network.ConnectivityChecker
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException
import com.waddleup.core.base.usecase.Result
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

abstract class BaseRepository(
    private val dispatchersProvider: DispatchersProvider,
    private val connectivityChecker: ConnectivityChecker,
    protected val retrofit: Retrofit
) {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return withContext(dispatchersProvider.io) {
            try {
                if (!connectivityChecker.isNetworkAvailable()) {
                    return@withContext Result.Error(NoConnectivityException())
                }

                val response = apiCall()
                Result.Success(response)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Timber.e(e, "Repository call failed")
                Result.Error(mapExceptionToDataError(e))
            }
        }
    }

    @Suppress("unused")
    protected fun <T> Response<T>.extractHeaders(): Map<String, String> {
        val headers = mutableMapOf<String, String>()

        this.headers().toMultimap().forEach { (key, values) ->
            if (values.isNotEmpty()) {
                headers[key] = values.first()
            }
        }

        return headers
    }

    /**For now, I assume error json body as this:
     *
     * {
     *   "errorCode": "VALIDATION_ERROR", // Or some other specific code
     *   "message": "User input is invalid.",
     *   "details": {
     *      "field": "email",
     *      "issue": "Format is incorrect"
     *   }
     * }
     * */
    protected open fun mapExceptionToDataError(exception: Exception): Exception {
        return when (exception) {
            is IOException -> NetworkException(exception)
            is HttpException -> {
                val parsedError: ParsedApiException? = tryParseErrorBody(exception)
                if (parsedError != null) {
                    when (parsedError.getErrorCode()) {
                        "VALIDATION_ERROR" -> ValidationApiException(exception.code(), parsedError.apiErrorResponse, exception)
                        else -> parsedError
                    }
                } else {
                    mapHttpExceptionByCode(exception)
                }
            }
            else -> exception // Unknown exception type
        }
    }

    private fun tryParseErrorBody(exception: HttpException): ParsedApiException? {
        return try {
            val errorBody: ResponseBody? = exception.response()?.errorBody()
            if (errorBody == null) {
                Timber.w("HttpException error body is null for code ${exception.code()}")
                return null
            }

            val converter: Converter<ResponseBody, ApiErrorResponse> = retrofit.responseBodyConverter(
                ApiErrorResponse::class.java,
                arrayOfNulls<Annotation>(0)
            )

            val apiErrorResponse: ApiErrorResponse? = converter.convert(errorBody)
            if (apiErrorResponse != null) {
                ParsedApiException(exception.code(), apiErrorResponse, exception)
            } else {
                Timber.w("Parsed ApiErrorResponse is null for code ${exception.code()}")
                null // Parsing succeeded but result was null
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to parse HttpException error body for code ${exception.code()}")
            null // Parsing failed
        }
    }

    private fun mapHttpExceptionByCode(exception: HttpException): Exception {
        return when (exception.code()) {
            401 -> UnauthorizedException(exception)
            403 -> ForbiddenException(exception)
            404 -> NotFoundException(exception)
            in 500..599 -> ServerException(exception)
            else -> UnknownApiException(exception)
        }
    }
}