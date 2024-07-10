package com.training.interviewtechnicaltest.pullrequests_feature.presentation.state

import com.training.interviewtechnicaltest.core.data.remote.response.pullrequests.PullRequestResponse


sealed class PullRequestsUiState{

    object Loading: PullRequestsUiState()
    object Error: PullRequestsUiState()

    data class SuccessPullRequestsUiState(
        val pullRequests: List<PullRequestResponse?> = emptyList(),
    ): PullRequestsUiState()

}