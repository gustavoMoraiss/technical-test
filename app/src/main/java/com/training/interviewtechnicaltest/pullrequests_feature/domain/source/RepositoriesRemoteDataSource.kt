package com.training.interviewtechnicaltest.pullrequests_feature.domain.source

import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.data.remote.response.repositories.RepositoriesResponse
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.PullRequest
import com.training.interviewtechnicaltest.core.paging.RepositoriesPageSource

interface PullRequestsRemoteDataSource {

    suspend fun getPullRequests(author: String, repo: String): ResultData<List<PullRequest?>>
}