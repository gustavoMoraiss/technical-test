package com.training.interviewtechnicaltest.features.repositories.data.source

import com.training.interviewtechnicaltest.core.data.remote.paging.RepositoriesPageSource
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.features.repositories.domain.source.RepositoriesRemoteDataSource
import javax.inject.Inject

class RepositoriesRemoteDataSourceImpl @Inject constructor(
    private val service: RepositoriesService,
    private val safeApiCaller: SafeApiCaller
) : RepositoriesRemoteDataSource {
    override fun getRepositoriesPageSource(): RepositoriesPageSource {
        return RepositoriesPageSource(this, safeApiCaller)
    }

    override suspend fun getRepositories(page: Int): RepositoriesResponse {
        return service.getRepositories(page)
    }
}