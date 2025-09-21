package com.training.interviewtechnicaltest.features.pullrequest.domain.repository

import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.PullRequest

interface PullRequestsRepository {
    suspend fun getPullRequestsFromRepository(
        author: String,
        repo: String
    ): ResultData<List<PullRequest?>>
}