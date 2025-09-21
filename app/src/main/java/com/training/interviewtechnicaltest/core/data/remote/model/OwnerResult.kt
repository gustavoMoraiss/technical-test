package com.training.interviewtechnicaltest.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class OwnerResult(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val login: String,
){
    companion object{
        fun createEmpty() = OwnerResult(
            avatarUrl = "",
            login = ""
        )
    }
}

fun OwnerResult?.createSafe() = this ?: OwnerResult.createEmpty()