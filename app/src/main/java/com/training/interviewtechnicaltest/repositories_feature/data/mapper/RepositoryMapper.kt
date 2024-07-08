package com.training.interviewtechnicaltest.repositories_feature.data.mapper

import com.training.interviewtechnicaltest.core.data.remote.model.RepositorieResult
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.domain.model.Repository

fun List<RepositorieResult>.toRepository() = map { repositoryResult ->
    Repository(
        forksUrl = repositoryResult.forksUrl,
        stargazersUrl = repositoryResult.stargazersUrl,
        forksCount = repositoryResult.forksCount,
        name = repositoryResult.name,
        stargazersCount = repositoryResult.stargazersCount,
        description = repositoryResult.description,
        fullName = repositoryResult.fullName,
        owner = repositoryResult.owner
    )
}