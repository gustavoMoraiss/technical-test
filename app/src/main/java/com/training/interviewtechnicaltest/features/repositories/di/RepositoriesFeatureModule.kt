package com.training.interviewtechnicaltest.features.repositories.di

import com.training.interviewtechnicaltest.core.data.remote.service.RepositoriesService
import com.training.interviewtechnicaltest.core.data.remote.service.util.SafeApiCaller
import com.training.interviewtechnicaltest.features.repositories.data.repository.RepositoriesRepositoryImpl
import com.training.interviewtechnicaltest.features.repositories.data.source.RepositoriesRemoteDataSourceImpl
import com.training.interviewtechnicaltest.features.repositories.domain.repository.RepositoriesRepository
import com.training.interviewtechnicaltest.features.repositories.domain.source.RepositoriesRemoteDataSource
import com.training.interviewtechnicaltest.features.repositories.domain.usecase.RepositoriesUseCase
import com.training.interviewtechnicaltest.features.repositories.domain.usecase.RepositoriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesFeatureModule {

    @Provides
    @Singleton
    fun provideRepositoriesRemoteDataSource(
        service: RepositoriesService,
        safeApiCaller: SafeApiCaller
    ): RepositoriesRemoteDataSource {
        return RepositoriesRemoteDataSourceImpl(service = service, safeApiCaller = safeApiCaller)
    }

    @Provides
    @Singleton
    fun provideRepositoriesRepository(remoteDataSource: RepositoriesRemoteDataSource): RepositoriesRepository {
        return RepositoriesRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRepositoriesUseCase(repository: RepositoriesRepository): RepositoriesUseCase {
        return RepositoriesUseCaseImpl(repository = repository)
    }
}