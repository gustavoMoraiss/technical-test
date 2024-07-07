package com.training.interviewtechnicaltest.core.data.remote.response.pullrequests

import com.google.gson.annotations.SerializedName
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse

data class PullRequestResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("head")
    val head: Head,
)

data class Head(
    @SerializedName("user")
    val user: User,
    @SerializedName("repo")
    val repo: RepositoriesResponse
)

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val login: String,
)
