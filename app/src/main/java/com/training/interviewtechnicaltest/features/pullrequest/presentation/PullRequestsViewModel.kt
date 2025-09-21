package com.training.interviewtechnicaltest.features.pullrequest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.interviewtechnicaltest.core.data.remote.service.util.onEmpty
import com.training.interviewtechnicaltest.core.data.remote.service.util.onError
import com.training.interviewtechnicaltest.core.data.remote.service.util.onSuccess
import com.training.interviewtechnicaltest.features.pullrequest.domain.usecase.PullRequestsUseCase
import com.training.interviewtechnicaltest.features.pullrequest.presentation.state.PullRequestsUiState
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

    private val _authorMutableStateFlow = MutableStateFlow("")

    val authorMutableStateFlow: StateFlow<String>
        get() = _authorMutableStateFlow


    private val _repoMutableStateFlow = MutableStateFlow("")

    val repoMutableStateFlow: StateFlow<String>
        get() = _repoMutableStateFlow

    private val _uiState = MutableStateFlow<PullRequestsUiState>(
        PullRequestsUiState.Loading
    )

    val uiState: StateFlow<PullRequestsUiState>
        get() = _uiState.asStateFlow()

    fun getPullRequests() {
        _uiState.update {
            PullRequestsUiState.Loading
        }
        viewModelScope.launch(Dispatchers.IO) {
            pullRequestsUseCase.invoke(
                author = _authorMutableStateFlow.value,
                repo = _repoMutableStateFlow.value
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
            }.onEmpty {
                _uiState.update {
                    PullRequestsUiState.EmptyData
                }
            }
        }
    }

    fun saveValues(author: String, repo: String) {
        _authorMutableStateFlow.value = author
        _repoMutableStateFlow.value = repo
    }
}