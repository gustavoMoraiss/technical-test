package com.training.interviewtechnicaltest.repositories_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.repositories_feature.domain.repository.RepositoriesRepository
import com.training.interviewtechnicaltest.repositories_feature.domain.source.RepositoriesRemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoriesRepositoryImpl constructor(
    private val remoteDataSource: RepositoriesRemoteDataSource
) : RepositoriesRepository {
    override fun getRepositories(pagingConfig: PagingConfig): Flow<PagingData<Repository>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getRepositoriesPageSource()
            }
        ).flow
    }
}