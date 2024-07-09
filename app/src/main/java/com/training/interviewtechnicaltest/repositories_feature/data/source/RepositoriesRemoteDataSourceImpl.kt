package com.training.interviewtechnicaltest.repositories_feature.data.source

import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.core.data.remote.service.util.APIException
import com.training.interviewtechnicaltest.core.data.remote.service.util.ActionErrorTypeEnum
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.core.data.remote.service.util.onEmpty
import com.training.interviewtechnicaltest.core.data.remote.service.util.onError
import com.training.interviewtechnicaltest.core.data.remote.service.util.onSuccess
import com.training.interviewtechnicaltest.core.paging.RepositoriesPageSource
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import javax.inject.Inject
import javax.xml.transform.Result

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