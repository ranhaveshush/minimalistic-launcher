package com.ranhaveshush.launcher.minimalistic.api

import retrofit2.Response

/**
 * This class decouples the used networking library (e.g. Retrofit) from the rest of the app.
 *
 * Used by API clients to transform specific API response
 * (e.g. Retrofit [Response][retrofit2.Response]) to [ApiResponse].
 *
 * Used by repositories to transform the [API response][ApiResponse] to a [resource][Resource].
 *
 * @param T The type of the response data object
 */
sealed class ApiResponse<T> {
    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"

        /**
         * Creates an [ErrorApiResponse] from a given [error][Throwable].
         */
        fun <T> create(error: Throwable): ApiResponse<T> {
            return ErrorApiResponse(error.message ?: UNKNOWN_ERROR, error)
        }

        /**
         * Creates an [ApiResponse] from a given Retrofit [Response][retrofit2.Response].
         */
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val data = response.body()
                if (data == null || response.code() == 204) {
                    EmptyApiResponse()
                } else {
                    SuccessApiResponse(data)
                }
            } else {
                val errorMessage = response.errorBody()?.string()
                val message = if (errorMessage.isNullOrEmpty()) {
                    response.message()
                } else {
                    errorMessage
                }
                ErrorApiResponse(message ?: UNKNOWN_ERROR)
            }
        }
    }
}

/**
 * An empty success response.
 */
class EmptyApiResponse<T> : ApiResponse<T>()

/**
 * A success response with a typed [T] data as it's body.
 */
data class SuccessApiResponse<T>(val data: T) : ApiResponse<T>()

/**
 * An error response with a message and an optional [cause][Throwable].
 */
data class ErrorApiResponse<T>(
    val message: String,
    val cause: Throwable? = null
) : ApiResponse<T>()
