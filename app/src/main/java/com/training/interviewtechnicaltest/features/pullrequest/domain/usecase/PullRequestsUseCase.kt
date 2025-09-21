package com.training.interviewtechnicaltest.features.pullrequest.domain.usecase

import com.training.interviewtechnicaltest.core.data.remote.service.util.ResultData
import com.training.interviewtechnicaltest.core.domain.model.PullRequest
import com.training.interviewtechnicaltest.features.pullrequest.domain.repository.PullRequestsRepository
import javax.inject.Inject

interface PullRequestsUseCase {
    suspend operator fun invoke(author: String, repo: String): ResultData<List<PullRequest?>>
}

class PullRequestsUseCaseImpl @Inject constructor(
    private val repository: PullRequestsRepository
) : PullRequestsUseCase {
    override suspend fun invoke(author: String, repo: String): ResultData<List<PullRequest?>> {
        return repository.getPullRequestsFromRepository(
            author = author,
            repo = repo
        )
    }
}