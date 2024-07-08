package com.training.interviewtechnicaltest.core.data.remote.response.pullrequests

import HeadResult
import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("head")
    val head: HeadResult,
)

