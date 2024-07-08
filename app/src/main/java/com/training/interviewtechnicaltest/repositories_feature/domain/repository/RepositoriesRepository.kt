package com.training.interviewtechnicaltest.repositories_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {
    fun getRepositories(pagingConfig: PagingConfig): Flow<PagingData<Repository>>
}