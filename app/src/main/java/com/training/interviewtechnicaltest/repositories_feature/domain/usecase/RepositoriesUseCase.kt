package com.training.interviewtechnicaltest.repositories_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.Repository
import com.training.interviewtechnicaltest.repositories_feature.domain.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface RepositoriesUseCase {
    operator fun invoke(): Flow<PagingData<Repository>>
}

class RepositoriesUseCaseImpl @Inject constructor(
    private val repository: RepositoriesRepository
) : RepositoriesUseCase {
    override fun invoke(): Flow<PagingData<Repository>> {
        return repository.getRepositories(
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        ).flowOn(Dispatchers.IO)
    }
}