package com.training.interviewtechnicaltest.features.pullrequest.domain.source

import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.PullRequest

interface PullRequestsRemoteDataSource {

    suspend fun getPullRequests(author: String, repo: String): ResultData<List<PullRequest?>>
}