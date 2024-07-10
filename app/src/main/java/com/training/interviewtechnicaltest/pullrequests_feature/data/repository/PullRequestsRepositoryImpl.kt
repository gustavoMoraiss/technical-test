package com.training.interviewtechnicaltest.pullrequests_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.pullrequests_feature.domain.repository.PullRequestsRepository
import com.training.interviewtechnicaltest.pullrequests_feature.domain.source.PullRequestsRemoteDataSource
import com.training.interviewtechnicaltest.repositories_feature.domain.repository.RepositoriesRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import kotlinx.coroutines.flow.Flow

class PullRequestsRepositoryImpl constructor(
    private val remoteDataSource: PullRequestsRemoteDataSource
) : PullRequestsRepository {

    override suspend fun getPullRequestsFromRepository(
        author: String,
        repo: String
    ): ResultData<List<PullRequestResponse?>> {
        return remoteDataSource.getPullRequests(
            author = author,
            repo = repo
        )
    }
}