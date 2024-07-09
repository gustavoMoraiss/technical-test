package com.training.interviewtechnicaltest.core.data.remote.service.util

import com.training.interviewtechnicaltest.core.data.remote.service.util.NetworkConstants.HTTP_STATUS_500
import retrofit2.Response

class APIException internal constructor(
    message: String? = null,
    val httpStatusCode: Int? = HTTP_STATUS_500,
    val actionErrorType: ActionErrorTypeEnum,
    val response: Response<*>? = null,
    val networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
) : RuntimeException(message) {

    companion object {
        fun httpError(
            response: Response<*>?,
            httpStatusCode: Int?,
            actionErrorType: ActionErrorTypeEnum,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = actionErrorType
            val message = response?.code().toString() + " " + response?.message()
            return APIException(
                message = message,
                response = response,
                httpStatusCode = httpStatusCode,
                actionErrorType = actionErrorType,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun unexpectedError(
            actionErrorType: ActionErrorTypeEnum,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = actionErrorType

            return APIException(
                actionErrorType = actionErrorType,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun networkError(
            message: String,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = ActionErrorTypeEnum.NETWORK_ERROR

            return APIException(
                message = message,
                actionErrorType = ActionErrorTypeEnum.NETWORK_ERROR,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun invalidToken(
            response: Response<*>?,
            httpStatusCode: Int?,
            actionErrorType: ActionErrorTypeEnum,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = actionErrorType
            val message = response?.code().toString() + " " + response?.message()
            return APIException(
                message = message,
                response = response,
                httpStatusCode = httpStatusCode,
                actionErrorType = actionErrorType,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun unauthorizedError(
            response: Response<*>?,
            httpStatusCode: Int?,
            message: String?,
            actionErrorType: ActionErrorTypeEnum,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = actionErrorType

            return APIException(
                message = message,
                response = response,
                httpStatusCode = httpStatusCode,
                actionErrorType = actionErrorType,
                networkErrorMessage = networkErrorMessage
            )
        }
    }
}