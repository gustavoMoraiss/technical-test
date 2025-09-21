package com.training.interviewtechnicaltest.features.pullrequest.presentation.state

import com.training.interviewtechnicaltest.core.domain.model.PullRequest

sealed class PullRequestsUiState{

    data object Loading: PullRequestsUiState()
    data object Error: PullRequestsUiState()
    data object EmptyData: PullRequestsUiState()
    data class SuccessPullRequestsUiState(
        val pullRequests: List<PullRequest?> = emptyList(),
    ): PullRequestsUiState()
}