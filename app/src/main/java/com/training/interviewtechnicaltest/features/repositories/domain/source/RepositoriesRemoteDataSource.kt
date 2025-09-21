package com.training.interviewtechnicaltest.features.repositories.domain.source

import com.training.interviewtechnicaltest.core.data.remote.paging.RepositoriesPageSource
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse

interface RepositoriesRemoteDataSource {

    fun getRepositoriesPageSource(): RepositoriesPageSource
    suspend fun getRepositories(page: Int): RepositoriesResponse
}