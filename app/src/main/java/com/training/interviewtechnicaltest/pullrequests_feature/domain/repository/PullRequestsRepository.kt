package com.training.interviewtechnicaltest.pullrequests_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface PullRequestsRepository {
    suspend fun getPullRequestsFromRepository(
        author: String,
        repo: String
    ): ResultData<List<PullRequestResponse?>>
}