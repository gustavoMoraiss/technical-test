package com.training.interviewtechnicaltest.core.util

sealed class ResultData<out T> {
    data object Loading : ResultData<Nothing>()
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Failure(val exception: Exception) : ResultData<Nothing>()
}