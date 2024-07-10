package com.training.interviewtechnicaltest.pullrequests_feature.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.interviewtechnicaltest.core.data.remote.service.util.onError
import com.training.interviewtechnicaltest.core.data.remote.service.util.onSuccess
import com.training.interviewtechnicaltest.pullrequests_feature.domain.usecase.PullRequestsUseCase
import com.training.interviewtechnicaltest.pullrequests_feature.presentation.state.PullRequestsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestsViewModel @Inject constructor(
    private val pullRequestsUseCase: PullRequestsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PullRequestsUiState>(
        PullRequestsUiState.Loading
    )

    val uiState: StateFlow<PullRequestsUiState>
        get() = _uiState.asStateFlow()

    init {
        getPullRequests()
    }

     fun getPullRequests(author: String = "krahets", repo: String = "hello-algo") {
        _uiState.update {
            PullRequestsUiState.Loading
        }
        viewModelScope.launch(Dispatchers.IO) {
            pullRequestsUseCase.invoke(
                author = author,
                repo = repo
            ).onSuccess { pullRequestResponse ->
                _uiState.update {
                    PullRequestsUiState.SuccessPullRequestsUiState(
                        pullRequests = pullRequestResponse
                    )
                }
            }.onError {
                _uiState.update {
                    PullRequestsUiState.Error
                }
            }
        }
    }
}