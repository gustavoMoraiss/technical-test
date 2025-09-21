package com.training.interviewtechnicaltest.features.repositories.data.mapper

import com.training.interviewtechnicaltest.core.data.remote.model.RepositoryResponse
import com.training.interviewtechnicaltest.core.data.remote.model.createSafe
import com.training.interviewtechnicaltest.core.domain.model.RepositoryModel
import com.training.interviewtechnicaltest.core.util.UtilFunctions.orZero

fun List<RepositoryResponse>.toRepositoryModel() = map { repositoryResult ->
    RepositoryModel(
        forksUrl = repositoryResult.forksUrl.orEmpty(),
        stargazersUrl = repositoryResult.stargazersUrl.orEmpty(),
        forksCount = repositoryResult.forksCount.orZero(),
        name = repositoryResult.name.orEmpty(),
        stargazersCount = repositoryResult.stargazersCount.orZero(),
        description = repositoryResult.description.orEmpty(),
        fullName = repositoryResult.fullName.orEmpty(),
        owner = repositoryResult.owner.createSafe()
    )
}