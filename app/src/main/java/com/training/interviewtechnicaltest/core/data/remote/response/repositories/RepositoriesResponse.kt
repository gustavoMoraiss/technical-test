package com.training.interviewtechnicaltest.core.data.remote.response.repositories

import com.google.gson.annotations.SerializedName
import com.training.interviewtechnicaltest.core.data.remote.model.RepositorieResponse

data class RepositoriesResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val repositories: List<RepositorieResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)