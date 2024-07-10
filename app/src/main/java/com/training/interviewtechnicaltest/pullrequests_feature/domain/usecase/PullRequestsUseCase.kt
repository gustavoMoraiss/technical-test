package com.training.interviewtechnicaltest.pullrequests_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse
import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.pullrequests_feature.domain.repository.PullRequestsRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface PullRequestsUseCase {
    suspend operator fun invoke(author: String, repo: String): ResultData<List<PullRequestResponse?>>
}

class PullRequestsUseCaseImpl @Inject constructor(
    private val repository: PullRequestsRepository
) : PullRequestsUseCase {
    override suspend fun invoke(author: String, repo: String): ResultData<List<PullRequestResponse?>> {
        return repository.getPullRequestsFromRepository(
            author = author,
            repo = repo
        )
    }
}