package com.training.interviewtechnicaltest.features.repositories.presentation.state

import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class RepositoriesState(
    var repositories: Flow<PagingData<RepositoryModel>> = emptyFlow()
)

