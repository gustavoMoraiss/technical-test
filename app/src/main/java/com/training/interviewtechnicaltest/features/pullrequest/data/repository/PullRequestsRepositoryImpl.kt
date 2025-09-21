package com.training.interviewtechnicaltest.features.pullrequest.data.repository

import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.PullRequest
import com.training.interviewtechnicaltest.features.pullrequest.domain.repository.PullRequestsRepository
import com.training.interviewtechnicaltest.features.pullrequest.domain.source.PullRequestsRemoteDataSource

class PullRequestsRepositoryImpl constructor(
    private val remoteDataSource: PullRequestsRemoteDataSource
) : PullRequestsRepository {

    override suspend fun getPullRequestsFromRepository(
        author: String,
        repo: String
    ): ResultData<List<PullRequest?>> {
        return remoteDataSource.getPullRequests(
            author = author,
            repo = repo
        )
    }
}