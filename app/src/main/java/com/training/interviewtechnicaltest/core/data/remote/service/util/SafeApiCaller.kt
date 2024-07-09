package com.training.interviewtechnicaltest.core.data.remote.service.util

import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class SafeApiCaller(private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = backgroundDispatcher,
        apiCall: suspend () -> T
    ): ResultData<T> {
        return withContext(dispatcher) {
            callApi(apiCall)
        }
    }

    private suspend fun <T> callApi(apiCall: suspend () -> T): ResultData<T> {
        return try {
            val result = apiCall.invoke()

            if (result is Response<*> && !result.isSuccessful) {
                val httpError = getAPIErrorAndCreateAPIException(result)
                ResultData.APIError(httpError)
            } else {
                if (result is Response<*> && result.body().toString().isNullOrEmpty()) {
                    ResultData.Empty(result.code())
                } else {
                    ResultData.Success(result)
                }
            }

        } catch (exception: Exception) {
            //TODO: log on firebase crashlytics
            val result = onError(exception)
            ResultData.APIError(result)
        }
    }

    private fun onError(exception: Exception): APIException {
        return when (exception) {
            is IOException -> {
                APIException.networkError(exception.message ?: "Network Error")
            }

            is HttpException -> {
                getAPIErrorAndCreateAPIException(exception.response())
            }

            is JsonSyntaxException -> {
                generateGenericHttpError(null)
            }

            else -> {
                APIException.unexpectedError(
                    actionErrorType = ActionErrorTypeEnum.NETWORK_ERROR
                )
            }
        }
    }

    private fun getAPIErrorAndCreateAPIException(response: Response<*>?): APIException {
        val networkErrorMessage = NetworkErrorMessage(response?.errorBody().toString())

        val errorCode = response?.code()
        val errorMessage = response?.message()

        return generateGenericHttpError(response, networkErrorMessage)
    }

    private fun generateGenericHttpError(
        response: Response<*>?,
        networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
    ): APIException {
        return APIException.httpError(
            response = response,
            httpStatusCode = response?.code(),
            actionErrorType = ActionErrorTypeEnum.HTTP_ERROR,
            networkErrorMessage = networkErrorMessage
        )
    }
}