package com.training.interviewtechnicaltest.features.repositories.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.RepositoryModel
import com.training.interviewtechnicaltest.features.repositories.domain.repository.RepositoriesRepository
import com.training.interviewtechnicaltest.features.repositories.domain.source.RepositoriesRemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoriesRepositoryImpl constructor(
    private val remoteDataSource: RepositoriesRemoteDataSource
) : RepositoriesRepository {
    override fun getRepositories(pagingConfig: PagingConfig): Flow<PagingData<RepositoryModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getRepositoriesPageSource()
            }
        ).flow
    }
}