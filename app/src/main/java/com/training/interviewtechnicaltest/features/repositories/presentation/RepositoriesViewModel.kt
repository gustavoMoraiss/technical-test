package com.training.interviewtechnicaltest.features.repositories.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.training.interviewtechnicaltest.features.repositories.domain.usecase.RepositoriesUseCase
import com.training.interviewtechnicaltest.features.repositories.presentation.state.RepositoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val repositoriesUseCase: RepositoriesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(RepositoriesState())
        private set

    init {
        getRepositories()
    }

    fun getRepositories() {
        try {
            val repositories = repositoriesUseCase.invoke()
                .cachedIn(viewModelScope)
            uiState = uiState.copy(repositories = repositories)
        } catch (e: Exception) {
            uiState = uiState.copy(repositories = flowOf())
        }
    }

}