package com.training.interviewtechnicaltest.core.domain.model

import HeadResult
import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("head")
    val head: HeadResult,
)

