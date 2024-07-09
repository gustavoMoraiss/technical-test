package com.training.interviewtechnicaltest.repositories_feature.presentation.state

import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

data class RepositoriesState(
    var repositories: Flow<PagingData<Repository>> = emptyFlow()
)

