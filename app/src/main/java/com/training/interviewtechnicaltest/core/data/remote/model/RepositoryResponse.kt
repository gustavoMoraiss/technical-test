package com.training.interviewtechnicaltest.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("forks_count")
    val forksCount: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("forks_url")
    val forksUrl: String? = null,
    @SerializedName("stargazers_url")
    val stargazersUrl: String? = null,
    @SerializedName("owner")
    val owner: OwnerResult? = null,
)