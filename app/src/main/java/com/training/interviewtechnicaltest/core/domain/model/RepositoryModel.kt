package com.training.interviewtechnicaltest.core.domain.model

import com.training.interviewtechnicaltest.core.data.remote.model.OwnerResult

data class RepositoryModel(
    val forksCount: Int,
    val name: String,
    val stargazersCount: Int,
    val description: String,
    val fullName: String,
    val forksUrl: String,
    val stargazersUrl: String,
    val owner: OwnerResult,
)