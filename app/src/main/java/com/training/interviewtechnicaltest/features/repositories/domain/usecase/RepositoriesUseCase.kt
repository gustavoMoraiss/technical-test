package com.training.interviewtechnicaltest.features.repositories.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.training.interviewtechnicaltest.core.domain.model.RepositoryModel
import com.training.interviewtechnicaltest.features.repositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface RepositoriesUseCase {
    operator fun invoke(): Flow<PagingData<RepositoryModel>>
}

class RepositoriesUseCaseImpl @Inject constructor(
    private val repository: RepositoriesRepository
) : RepositoriesUseCase {
    override fun invoke(): Flow<PagingData<RepositoryModel>> {
        return repository.getRepositories(
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        ).flowOn(Dispatchers.IO)
    }
}