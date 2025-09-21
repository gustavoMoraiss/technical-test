package com.training.interviewtechnicaltest.features.repositories.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {
    fun getRepositories(pagingConfig: PagingConfig): Flow<PagingData<RepositoryModel>>
}