package com.training.interviewtechnicaltest.repositories_feature.domain.source

import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.paging.RepositoriesPageSource

interface RepositoriesRemoteDataSource {

    fun getRepositoriesPageSource(): RepositoriesPageSource
    suspend fun getRepositories(page: Int): RepositoriesResponse
}