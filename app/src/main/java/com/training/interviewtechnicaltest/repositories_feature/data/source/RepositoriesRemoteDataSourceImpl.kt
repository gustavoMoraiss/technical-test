package com.training.interviewtechnicaltest.repositories_feature.data.source

import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.core.paging.RepositoriesPageSource
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import javax.inject.Inject

class RepositoriesRemoteDataSourceImpl @Inject constructor(
    private val service: RepositoriesService
) :
    RepositoriesRemoteDataSource {
    override fun getRepositoriesPageSource(): RepositoriesPageSource {
        return RepositoriesPageSource(this)
    }

    override suspend fun getRepositories(page: Int): RepositoriesResponse {
        return service.getRepositories(page = page)
    }
}