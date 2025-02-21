package com.training.interviewtechnicaltest.repositories_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.training.interviewtechnicaltest.repositories_feature.domain.usecase.RepositoriesUseCase
import com.training.interviewtechnicaltest.repositories_feature.presentation.state.RepositoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

     private fun getRepositories() {
         try {
             val repositories = repositoriesUseCase.invoke()
                 .cachedIn(viewModelScope)
             uiState = uiState.copy(repositories = repositories)
         } catch (e: Exception){
             uiState = uiState.copy(repositories = flowOf())
         }
    }

}