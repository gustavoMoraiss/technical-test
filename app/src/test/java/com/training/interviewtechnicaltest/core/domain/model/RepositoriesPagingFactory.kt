package com.training.interviewtechnicaltest.core.domain.model

import com.training.interviewtechnicaltest.core.data.remote.model.OwnerResult
import com.training.interviewtechnicaltest.core.data.remote.model.RepositoryResult
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse

class RepositoriesPagingFactory {

    companion object {
        fun create(repositoryType: RepositoriesType) = when (repositoryType) {
            is RepositoriesType.Default -> {
                RepositoriesResponse(
                    totalCount = 200,
                    incompleteResults = false,
                    repositories = listOf(
                        RepositoryResult(
                            description = "fake description,",
                            forksCount = 10,
                            stargazersCount = 10,
                            fullName = "fake name",
                            owner = OwnerResult(
                                avatarUrl = "fake url",
                                login = "fake login"
                            ),
                            forksUrl = "fake url",
                            stargazersUrl = "fake url",
                            name = "fake name"
                        ),
                        RepositoryResult(
                            description = "fake description,",
                            forksCount = 10,
                            stargazersCount = 10,
                            fullName = "fake name",
                            owner = OwnerResult(
                                avatarUrl = "fake url",
                                login = "fake login"
                            ),
                            forksUrl = "fake url",
                            stargazersUrl = "fake url",
                            name = "fake name"
                        ),
                        RepositoryResult(
                            description = "fake description,",
                            forksCount = 10,
                            stargazersCount = 10,
                            fullName = "fake name",
                            owner = OwnerResult(
                                avatarUrl = "fake url",
                                login = "fake login"
                            ),
                            forksUrl = "fake url",
                            stargazersUrl = "fake url",
                            name = "fake name"
                        )
                    )
                )
            }

            is RepositoriesType.EmptyListRepositories -> RepositoriesResponse(
                totalCount = 200,
                incompleteResults = false,
                repositories = emptyList()
            )
        }
    }

    sealed class RepositoriesType {
        object EmptyListRepositories : RepositoriesType()
        object Default : RepositoriesType()
    }
}