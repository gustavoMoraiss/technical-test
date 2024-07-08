package com.training.interviewtechnicaltest.core.domain.model

import com.google.gson.annotations.SerializedName
import com.training.interviewtechnicaltest.core.data.remote.model.OwnerResult

data class Repository(
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("forks_url")
    val forksUrl: String,
    @SerializedName("stargazers_url")
    val stargazersUrl: String,
    @SerializedName("owner")
    val owner: OwnerResult,
)